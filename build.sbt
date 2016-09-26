organization := "com.dragisak"

name := "monocle-cats"

version := "0.1.2-SNAPSHOT"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.10.5", "2.11.8")

val catsVersion = "0.7.2"

val monocleVersion = "1.2.2"

libraryDependencies ++= Seq(
  "org.typelevel"               %% "cats"           % catsVersion,
  "com.github.julien-truffaut"  %% "monocle-core"   % monocleVersion,
  "com.github.julien-truffaut"  %% "monocle-macro"  % monocleVersion  % Test,
  "org.scalatest"               %% "scalatest"      % "3.0.0"         % Test

)

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-unused-import",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-language:higherKinds"
)

scalastyleFailOnError := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
