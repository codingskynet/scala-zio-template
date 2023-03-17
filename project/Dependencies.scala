import sbt._

object Dependencies {
  private val zio = Seq(
    "dev.zio" %% "zio" % Version.Zio,
    "dev.zio" %% "zio-test" % Version.Zio % Test,
    "dev.zio" %% "zio-test-sbt" % Version.Zio % Test,
    "dev.zio" %% "zio-test-magnolia" % Version.Zio % Test
  )

  private val scalapbRuntime = Seq(
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
  )

  private val scalatest = Seq(
    "org.scalatest" %% "scalatest" % Version.Scalatest % Test
  )

  val domain: Seq[ModuleID] =
    zio ++ scalatest

  val app: Seq[ModuleID] = Nil

  val infra: Seq[ModuleID] = Nil

  val presentation: Seq[ModuleID] = scalapbRuntime

  val boot: Seq[ModuleID] = Nil
}
