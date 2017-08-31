name := "webapp"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.14",
  "org.webjars" % "lodash" % "4.17.4",
  "org.webjars" % "react" % "15.6.1",
  "org.webjars.npm" % "react-dom" % "15.6.1",
  "org.webjars.npm" % "react-redux" % "5.0.5",
  "org.apache.spark" % "spark-core_2.11" % "2.1.0",
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0",
  "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.1"

)

libraryDependencies += guice

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7"

pipelineStages := Seq(digest)
