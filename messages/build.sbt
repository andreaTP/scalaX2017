lazy val root =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name := "twitter-messages",
      organization := "demo.akkajs",
      version := "0.0.1",
      scalaVersion := "2.12.4",
      libraryDependencies +=
        "com.github.benhutchison" %%% "prickle" % "1.1.13"
    )
