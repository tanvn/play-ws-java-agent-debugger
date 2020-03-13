import sbt.Keys._
import play.sbt.PlaySettings
import com.lightbend.sbt.javaagent.JavaAgent
import com.lightbend.sbt.javaagent.JavaAgent.JavaAgentKeys.javaAgents
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

lazy val root = (project in file("."))
  .enablePlugins(PlayService, PlayLayoutPlugin, Common,JavaAgent, JavaAppPackaging)
  .settings(
    name := "play-ws-java-agent-debugger",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "org.joda" % "joda-convert" % "2.2.1",
      "net.logstash.logback" % "logstash-logback-encoder" % "6.2",
      "io.lemonlabs" %% "scala-uri" % "1.5.1",
      "net.codingwell" %% "scala-guice" % "4.2.6",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
//      "com.datadoghq" % "dd-java-agent" % "0.41.0",
//      "com.datadoghq" % "dd-trace-api" % "0.41.0",
      "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.0.8",
      "com.typesafe.play" %% "play-ws-standalone-json" % "2.0.8",
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  ).settings(
  javaAgents += "com.datadoghq" % "dd-java-agent" % "0.46.0"
)

lazy val gatlingVersion = "3.3.1"
lazy val gatling = (project in file("gatling"))
  .enablePlugins(GatlingPlugin)
  .settings(
    scalaVersion := "2.12.10",
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
      "io.gatling" % "gatling-test-framework" % gatlingVersion % Test
    )
  )

// Documentation for this project:
//    sbt "project docs" "~ paradox"
//    open docs/target/paradox/site/index.html
lazy val docs = (project in file("docs")).enablePlugins(ParadoxPlugin).
  settings(
    scalaVersion := "2.13.1",
    paradoxProperties += ("download_url" -> "https://example.lightbend.com/v1/download/play-samples-play-scala-rest-api-example")
  )
