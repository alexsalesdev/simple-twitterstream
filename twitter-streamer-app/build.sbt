name := "twitter-streamer-app"


val twitterVersion = "6.45.0"
val kafkaStreamsVersion = "0.10.0.0"
val hbcCoreVersion = "2.2.0"
val gsonVersion = "2.7"
val scalaLoggingVersion = "3.4.0"


libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.slf4j" % "slf4j-simple" % "1.7.21",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.9",
  "com.twitter" %% "util-core" % twitterVersion,
  "com.twitter" % "hbc-core" % hbcCoreVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.16",
  "com.google.code.gson" % "gson" % gsonVersion,
  "com.typesafe.play" %% "play-json" % "2.6.0-M1"
)
