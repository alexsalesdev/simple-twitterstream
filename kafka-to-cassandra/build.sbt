name := "kafka-to-cassandra"
scalaVersion := "2.11.8" // TODO no 2.12 yet for spark

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.1.0",
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0",
  "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.0-M3"
)
