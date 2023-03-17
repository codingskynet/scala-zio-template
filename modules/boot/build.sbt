name := "scala-zio-boot"

libraryDependencies ++= Dependencies.boot

Compile / run / mainClass := Some("dev.zero_cost.scala_zio.Main")
