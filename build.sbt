
name := "twitterstream"

version := "1.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",

  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
  )
)

lazy val kafkaCommon = (project in file("kafka-common")).settings(commonSettings: _*)

lazy val kafkaServer = (project in file("kafka-server")).settings(commonSettings: _*)

lazy val cassandraServer = (project in file("cassandra-server")).settings(commonSettings: _*)

lazy val kafkaToCassandra = (project in file("kafka-to-cassandra")).settings(commonSettings: _*).dependsOn(kafkaCommon)

lazy val twitterStreamerEngine = (project in file("twitter-streamer-app")).settings(commonSettings: _*).dependsOn(kafkaCommon)

lazy val wordCountEngine = (project in file("word-count-engine")).settings(commonSettings: _*).dependsOn(kafkaCommon)

lazy val webapp = (project in file("webapp")).settings(commonSettings: _*).dependsOn(kafkaCommon).enablePlugins(PlayScala, SbtWeb)