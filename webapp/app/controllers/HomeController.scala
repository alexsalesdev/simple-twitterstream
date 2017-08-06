package controllers

import javax.inject._

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import play.api.Configuration
import play.api.mvc.{Action, Controller, WebSocket}
import play.api.libs.json.{JsValue, Json}
import play.api.libs.streams.ActorFlow
import services.{Kafka, WordCountActor}

@Singleton
class HomeController @Inject() (kafka: Kafka, configuration: Configuration) extends Controller {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  def wordcount = Action { implicit request =>
    Ok(views.html.wordcount(routes.HomeController.wordcountWs().webSocketURL()))
  }

  // sink is incoming driver messages
  // source is outgoing rider messages
//  def wordcountWs = WebSocket.accept[String, String] { request =>
//    ActorFlow.actorRef { out =>
//      WordCountActor.props(kafka, configuration, out)
//    }
//  }

  def wordcountWs = WebSocket.accept[String, String] { _ =>
    val sink = Sink.foreach[String](println)

    val source = kafka.source("word-count")
      .map(f => Json.obj("key" -> f.key(), "val" -> f.value().toString).toString())
    Flow.fromSinkAndSource(sink, source)
  }



}
