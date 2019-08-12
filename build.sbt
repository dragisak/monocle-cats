organization := "com.dragishak"
name := "monocle-cats"
version := "1.3-RC1"
scalaVersion := "2.13.0"

crossScalaVersions := List("2.12.9", "2.13.0")

val monocleVersion = "2.0.0-RC1"

libraryDependencies ++= List(
  "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion % Test,
  "org.scalatest"              %% "scalatest"     % "3.0.8" % Test,
  "org.scala-lang"             % "scala-reflect"  % scalaVersion.value % Test
)

lazy val paradisePlugin = Def.setting {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, v)) if v <= 12 =>
      List(compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" % Test cross CrossVersion.patch))
    case _ =>
      // Scala 2.13, macro annotations merged into scala-reflect
      Nil
  }
}
libraryDependencies ++= paradisePlugin.value

scalacOptions ++= List(
  "-feature",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-language:higherKinds"
)

scalacOptions ++= PartialFunction
  .condOpt(CrossVersion.partialVersion(scalaVersion.value)) {
    case Some((2, n)) if n <= 12 => List("-Xfuture", "-Yno-adapted-args")
    case Some((2, n)) if n >= 13 => List("-Ymacro-annotations")
  }
  .toList
  .flatten

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
