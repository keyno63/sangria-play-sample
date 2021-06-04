logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin"   % "2.8.2")
addSbtPlugin("org.scalameta"     % "sbt-scalafmt" % "2.4.2")
