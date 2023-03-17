package dev.zero_cost.scala_zio

import zio._

object Main extends ZIOApp {
  type Environment = Any

  val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = ZLayer.empty

  val environmentTag: EnvironmentTag[Any] = EnvironmentTag[Any]

  def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] =
    Console.printLine("hello, world")
}
