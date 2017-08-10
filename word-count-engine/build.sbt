name := "word-count-engine"

val scalaLoggingVersion = "3.4.0"


libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.slf4j" % "slf4j-simple" % "1.7.21",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.8.4",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.16",
  "org.apache.kafka" % "kafka-streams" % "0.10.2.1",
  "com.typesafe.play" %% "play-json" % "2.5.12"
)
