lazy val `sangria-play-sample` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "sangria-play-sample",
    version := "1.0",
    libraryDependencies ++=
      Seq(jdbc, ehcache, ws, specs2 % Test, guice) ++
        Seq(
          // sangria
          "org.sangria-graphql"    %% "sangria"         % sangriaVersion,
          "org.sangria-graphql"    %% "sangria-slowlog" % sangriaSlowlogVersion,
          "org.sangria-graphql"    %% "sangria-circe"   % sangriaCirceVersion
        ) ++
        Seq(
          // circe(json libs)
          "io.circe" %% "circe-core",
          "io.circe" %% "circe-parser",
          "io.circe" %% "circe-generic"
        ).map(_ % circeVersion) ++
        Seq(
          "io.circe"     %% "circe-optics" % circeOptVersion,
          "com.dripower" %% "play-circe"   % circePlayVersion
        )
  )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.13.5"

lazy val sangriaVersion        = "2.1.3"
lazy val sangriaSlowlogVersion = "2.0.2"
lazy val sangriaCirceVersion   = "1.3.2"

lazy val circeVersion     = "0.12.1"
lazy val circeOptVersion  = "0.14.1"
lazy val circePlayVersion = "2812.0"

unmanagedResourceDirectories in Test += baseDirectory.value / "target/web/public/test"
