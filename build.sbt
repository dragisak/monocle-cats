organization := "com.dragishak"
name := "monocle-cats"
version := "1.3-SNAPSHOT"
scalaVersion := "2.12.1"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

val catsVersion    = "0.9.0"
val monocleVersion = "1.4.0"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats"          % catsVersion,
  "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion % Test,
  "org.scalatest"              %% "scalatest"     % "3.0.8" % Test
)

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-language:higherKinds"
)

scalastyleFailOnError := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/dragisak/monocle-cats"))

organizationName := "Dragisa Krsmanovic"

organizationHomepage := Some(url("https://github.com/dragisak"))

description := "Utilities for combining Cats and Monocle lenses"

pomExtra in Global := {
  <scm>
    <connection>scm:git:github.com:dragisak/monocle-cats.git</connection>
    <url>git@github.com:dragisak/monocle-cats.git</url>
  </scm>
  <developers>
    <developer>
      <id>dragisak</id>
      <name>Dragisa Krsmanovic</name>
      <url>https://github.com/dragisak/</url>
    </developer>
  </developers>
}

scalastyleFailOnError := true
(test in Test) := (test in Test)
  .dependsOn(
    (scalafmtCheck in Compile).toTask,
    (scalafmtCheck in Test).toTask,
    (scalafmtSbtCheck in Compile).toTask,
    (scalastyle in Compile).toTask("")
  )
  .value

(scalafmt in Compile) := (scalafmt in Test)
  .dependsOn((scalafmt in Compile).toTask, (scalafmtSbt in Compile).toTask)
  .value
