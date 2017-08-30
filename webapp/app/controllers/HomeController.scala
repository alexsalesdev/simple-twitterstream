package controllers

import javax.inject._

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc._
import services.Kafka


@Singleton
class HomeController @Inject() (cc: ControllerComponents, kafka : Kafka, configuration: Configuration) extends AbstractController(cc) {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  def wordcount = Action { implicit request =>
    Ok(views.html.wordcount(routes.HomeController.wordcountWs().webSocketURL()))
  }

  def wordcountWs = WebSocket.accept[String, String] { _ =>
    val sink = Sink.foreach[String](println)

    val source = kafka.source("word-count")
      .map(f => Json.obj("key" -> f.key(), "val" -> f.value().toString).toString())
    Flow.fromSinkAndSource(sink, source)
  }



}
