name := "webapp"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.14",
  "org.webjars" % "lodash" % "4.17.4",
  "org.webjars" % "react" % "15.6.1",
  "org.webjars.npm" % "react-dom" % "15.6.1",
  "org.webjars.npm" % "react-redux" % "5.0.5"

)

libraryDependencies += guice

pipelineStages := Seq(digest)
