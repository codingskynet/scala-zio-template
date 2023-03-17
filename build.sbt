ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

Global / PB.protocVersion := "3.18.2" // for apple aarch binary

val commonSettings = Seq(
  dependencyOverrides += "dev.zio" %% "zio" % Version.Zio,
  resolvers ++= Resolver.sonatypeOssRepos("snapshots")
)

val bothCompileAndTest = "compile->compile;test->test"

lazy val domain = project
  .in(file("modules/domain"))
  .settings(commonSettings)

lazy val app = project
  .in(file("modules/app"))
  .dependsOn(domain % bothCompileAndTest)
  .settings(commonSettings)

lazy val infra = project
  .in(file("modules/infra"))
  .dependsOn(domain % bothCompileAndTest)
  .settings(commonSettings)

lazy val presentation = project
  .in(file("modules/presentation"))
  .dependsOn(app % bothCompileAndTest)
  .settings(commonSettings)

lazy val boot = project
  .in(file("modules/boot"))
  .dependsOn(infra % bothCompileAndTest)
  .dependsOn(presentation % bothCompileAndTest)
  .settings(commonSettings)
