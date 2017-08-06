package services

import java.lang.Long
import java.util.UUID
import javax.inject.{Inject, Singleton}

import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Source
import helpers.KafkaHelper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.{LongDeserializer, StringDeserializer}
import play.api.Configuration


trait Kafka {
  def source(topic: String): Source[ConsumerRecord[String, Long], _]
}

@Singleton
class KafkaImpl @Inject() (configuration: Configuration) extends Kafka {

  def consumerSettings: ConsumerSettings[String, Long] = {
    val keyDeserializer = new StringDeserializer()
    val valueDeserializer = new LongDeserializer()
    val config = KafkaHelper.kafkaConfig(configuration.underlying, "akka.kafka.consumer")

    ConsumerSettings(config, keyDeserializer, valueDeserializer)
      .withBootstrapServers(KafkaHelper.kafkaUrl(configuration.underlying))
      .withGroupId(UUID.randomUUID().toString)
  }

  def source(topic: String): Source[ConsumerRecord[String, Long], _] = {
    val subscriptions = Subscriptions.topics(topic)
    Consumer.plainSource(consumerSettings, subscriptions)
  }

}
