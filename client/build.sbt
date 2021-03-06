lazy val root =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .dependsOn(ProjectRef(file("..") / "messages", "root"))
    .settings(
      scalaVersion := "2.12.4",
      scalacOptions := Seq("-feature", "-language:_", "-deprecation"),
      libraryDependencies ++= Seq(
        "com.lihaoyi" %%% "scalatags" % "0.6.3",
        "org.akka-js" %%% "akkajsactorstream" % "1.2.5.8",
        "org.akka-js" %%% "akkajstestkit" % "1.2.5.8" % "test",
        "org.akka-js" %%% "akkajsstreamtestkit" % "1.2.5.8" % "test"
      ),
      jsDependencies +=
        "org.webjars.bower" % "diff-dom" % "2.0.3" / "diffDOM.js",
      scalaJSUseMainModuleInitializer := true,
      skip in packageJSDependencies := false
    )
