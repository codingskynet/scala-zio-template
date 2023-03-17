ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"


val commonSettings = Seq(
  dependencyOverrides += "dev.zio" %% "zio" % Version.Zio
)

val bothCompileAndTest = "compile->compile;test->test"

lazy val domain = project
  .in(file("modules/domain"))

lazy val app = project
  .in(file("modules/app"))
  .dependsOn(domain % bothCompileAndTest)

lazy val infra = project
  .in(file("modules/infra"))
  .dependsOn(domain % bothCompileAndTest)

lazy val presentation = project
  .in(file("modules/presentation"))
  .dependsOn(app % bothCompileAndTest)

lazy val boot = project
  .in(file("modules/boot"))
  .dependsOn(infra % bothCompileAndTest)
  .dependsOn(presentation % bothCompileAndTest)
