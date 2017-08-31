package controllers

import javax.inject._

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink}
import akka.util.ByteString
import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}
import play.api.Configuration
import play.api.http.HttpEntity
import play.api.libs.json.Json
import play.api.mvc._
import services.Kafka


@Singleton
class HomeController @Inject() (cc: ControllerComponents, kafka : Kafka, configuration: Configuration) extends AbstractController(cc) {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
    .set("spark.driver.allowMultipleContexts", "true")
    .set("spark.sql.warehouse.dir", "spark-warehouse")
  val context = new SparkContext("local", "WebappCassandra", conf)


  def wordcount = Action { implicit request =>
    Ok(views.html.wordcount(routes.HomeController.wordcountWs().webSocketURL()))
  }

  def wordcountWs = WebSocket.accept[String, String] { _ =>
    val sink = Sink.foreach[String](println)

    val source = kafka.source("word-count")
      .map(f => Json.obj("key" -> f.key(), "val" -> f.value().toString).toString())

    Flow.fromSinkAndSource(sink, source)
  }

  def wordcountApi = Action {

    val resultMap = context.cassandraTable[(Long, String)]("wordcountapp", "wordcount")
      .select("value", "key")
      .sortByKey(false)
      .take(20)
      .toSeq

    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Strict(ByteString(Json.toJson(resultMap).toString()), Some("text/json"))
    )
  }




}
