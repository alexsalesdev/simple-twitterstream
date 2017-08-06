name := "webapp"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.14",
  "org.webjars" % "vue" % "2.1.3",
  "org.webjars" % "lodash" % "4.17.4"
)

pipelineStages := Seq(digest)
