import java.util.Properties

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.twitter.hbc.httpclient.auth.OAuth1
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import play.api.libs.json.Json

import scala.sys.addShutdownHook

object IngestApp extends App {
  val log = Logger("IngestApp")

  var closing = false

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("client.id", "tweet stream producer")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)

  // close gracefully
  addShutdownHook {
    closing = true
    tweetSource.hosebirdClient.stop
    producer.close()
  }

  val config = ConfigFactory.parseResources("application.conf")
  val twitterConfig = config.getConfig("twitter")
  val tweetSource = {
    val oAuth1 = new OAuth1(
      twitterConfig.getString("consumer_key"),
      twitterConfig.getString("consumer_secret"),
      twitterConfig.getString("token"),
      twitterConfig.getString("token_secret"))

    new TweetSource(oAuth1, filterTerms)
  }

  def filterTerms = {
    val terms = twitterConfig.getStringList("terms")
    Range(0, terms.size()).map { i => terms.get(i) }
  }

  while (!(tweetSource.hosebirdClient.isDone) & !(closing)) {
    tweetSource.take() match {
      case Some(json) =>
        log.info(s"tweetSource $json")
        val jsValue = Json.parse(json)
        (jsValue \ "text").as[String].split("\\W+")
          .foldLeft(Map.empty[String, Int]){
            (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
          }.foreach(f => {
          producer.send(new ProducerRecord("tweet", "", (jsValue \ "text").as[String]))
        })

      case None =>
        log.info(s"nothing to take")
    }
  }




}
