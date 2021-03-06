ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "count-word"
  )
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "3.1.0"
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "3.1.0"

libraryDependencies += "org.apache.kafka" % "kafka-streams-test-utils" % "3.1.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.36"