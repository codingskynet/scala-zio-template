package dev.zero_cost.scala_zio

import dev.zero_cost.scala_zio.ShortSiteRepository._
import zio.*

trait ShortSiteRepository {
  def create(url: String): IO[CreateFailure, ShortSite]

  def get(id: String): IO[GetFailure, ShortSite]
}

object ShortSiteRepository {
  sealed trait CreateFailure

  object CreateFailure {
    case object Exists extends CreateFailure
  }

  sealed trait GetFailure

  object GetFailure {
    case object NotFound extends GetFailure
  }
}
