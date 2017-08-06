package services


import akka.NotUsed
import akka.actor.{Actor, ActorRef, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink}
import play.api.Configuration
import play.api.libs.json.Json


object WordCountActor {
  def props(kafka: Kafka, configuration: Configuration, out: ActorRef) = Props(new WordCountActor(kafka, configuration, out))
}


class WordCountActor (kafka: Kafka, configuration: Configuration, outChannel: ActorRef)  extends Actor {
  import WordCountActor._
  implicit val mat = ActorMaterializer()

  override def preStart(): Unit = {
    println("WordCountActor started")

    kafka.source("word-count")
      .map(f => Map("key" -> f.key(), "val" -> f.value().toString))
      .runWith(Sink.foreach(f => outChannel !  Json.toJson(f.toMap).toString()))
  }

  override def postStop(): Unit = println("WordCountActor stopped")

  override def receive: Receive = {
    case s: String => outChannel ! s.reverse
  }
}


