lazy val root =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .dependsOn(ProjectRef(file("..") / "messages", "root"))
    .settings(
      scalaVersion := "2.12.4",
      libraryDependencies ++= Seq(
        "org.akka-js" %%% "akkajsactor" % "1.2.5.8",
        "org.akka-js" %%% "akkajstestkit" % "1.2.5.8" % "test"
      ),
      fork in run := true,
      cancelable in Global := true
    )
