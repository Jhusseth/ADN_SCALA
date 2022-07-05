import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

/**
  * Settings that are common to all the SBT projects
  */
object Common extends AutoPlugin {
  override def trigger = allRequirements
  override def requires: sbt.Plugins = JvmPlugin

  override def projectSettings = Seq(
    organization := "com.lightbend.restapi",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8", // yes, this is 2 args
      "-target:jvm-1.8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Ywarn-numeric-widen",
      "-Xfatal-warnings"
    ),
    resolvers ++= Seq(DefaultMavenRepository,
      Classpaths.typesafeReleases,
      Classpaths.sbtPluginReleases,
      "Typesafe Releases" at "https://repo.typesafe.com/typesafe/maven-releases/",
      "Apache Staging" at "https://repository.apache.org/content/repositories/staging/",
      "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
      "Java.net Maven2 Repository" at "https://download.java.net/maven/2/",
      "Eclipse repositories" at "https://repo.eclipse.org/service/local/repositories/egit-releases/content/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
      "SpinGo OSS" at "https://spingo-oss.s3.amazonaws.com/repositories/releases",
      "The New Motion Public Repo" at "https://nexus.thenewmotion.com/content/groups/public/",
      Resolver.bintrayRepo("iheartradio", "maven")
    ),
    Test / scalacOptions ++= Seq("-Yrangepos"),
    autoAPIMappings := true
  )
}
