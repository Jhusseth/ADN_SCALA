import sbt.Keys._

lazy val root = (project in file("."))
  .enablePlugins(PlayService, PlayLayoutPlugin, Common, PlayScala)
  .settings(
    name := "play-scala-project",
    libraryDependencies ++= Seq(
      cacheApi,
      guice,
      filters,
//      jdbc,
      evolutions,
      "joda-time" % "joda-time" % "2.10.14",
      "org.joda" % "joda-convert" % "2.2.2",
      "org.mockito" % "mockito-core" % "4.6.1" % "provided",
      "com.h2database" % "h2" % "2.1.212" % "provided",
      "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
      "mysql" % "mysql-connector-java" % "8.0.29",
      "net.logstash.logback" % "logstash-logback-encoder" % "7.2",
      "io.lemonlabs" %% "scala-uri" % "4.0.2",
      "net.codingwell" %% "scala-guice" % "5.1.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.specs2" %% "specs2-mock" % "4.16.0" % Test,
      "org.typelevel" %% "cats-core" % "2.7.0",
      "io.monix" %% "monix" % "3.4.1",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
      "com.typesafe.slick" %% "slick-codegen" % "3.3.3",
      "com.typesafe.play" %% "play-slick" % "5.0.2",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.2",
      "com.typesafe.play" %% "play-guice" % "2.8.16",
      "com.typesafe.play" %% "play-ws" % "2.8.16",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "com.typesafe.play" %% "filters-helpers" % "2.8.16",
      "com.typesafe.play" %% "play-test" % "2.8.16",
      "io.underscore" %% "slickless" % "0.3.6",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.3"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
      "-Xfatal-warnings",
      "-unchecked"
    )
  )

lazy val gatlingVersion = "3.7.6"
lazy val gatling = (project in file("gatling"))
  .enablePlugins(GatlingPlugin)
  .settings(
    scalaVersion := "2.12.15",
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
      "io.gatling" % "gatling-test-framework" % gatlingVersion % Test
    )
  )
