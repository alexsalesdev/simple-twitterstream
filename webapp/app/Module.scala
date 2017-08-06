import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import services.{Kafka, KafkaImpl}

class Module extends AbstractModule  with AkkaGuiceSupport {

  override def configure() = {
    bind(classOf[Kafka]).to(classOf[KafkaImpl])
  }

}
