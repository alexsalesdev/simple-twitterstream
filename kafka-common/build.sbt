name := "kafka-common"
scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.10.2.1",
  "com.github.jkutner" % "env-keystore" % "0.1.2",
  "com.typesafe.play" % "play-json_2.12" % "2.6.0-M1" exclude("com.fasterxml.jackson.core", "jackson-databind"),
  "com.github.kxbmap" %% "configs" % "0.4.4"
)
