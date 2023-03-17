addSbtPlugin("org.jetbrains.scala" % "sbt-ide-settings" % "1.1.1")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")
addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.2")

// when zio-grpc:6.0.0 is released, change it on maven.
libraryDependencies ++= Seq(
  "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % "0.0.0+1-fcae1bdd-SNAPSHOT",
  "com.thesamet.scalapb" %% "compilerplugin" % "0.11.13"
)
