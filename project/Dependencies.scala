import sbt._

object Dependencies {
  private val zio = Seq("dev.zio" %% "zio" % Version.Zio)

  val domain: Seq[ModuleID] =
    zio

  val app: Seq[ModuleID] = Nil

  val infra: Seq[ModuleID] = Nil

  val presentation: Seq[ModuleID] = Nil

  val boot: Seq[ModuleID] = Nil
}
