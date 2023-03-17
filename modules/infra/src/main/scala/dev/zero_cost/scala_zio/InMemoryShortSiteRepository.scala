package dev.zero_cost.scala_zio
import dev.zero_cost.scala_zio.ShortSiteRepository.*
import zio.*

import scala.collection.mutable

class InMemoryShortSiteRepository(
    private var siteSet: Set[String],
    private var siteList: mutable.ArrayBuffer[ShortSiteRow],
    private var count: Int
) extends ShortSiteRepository {
  private def exists(url: String): Boolean =
    siteSet.contains(url)

  override def create(url: String): IO[CreateFailure, ShortSite] =
    synchronized {
      if exists(url) then ZIO.fail(CreateFailure.Exists)
      else
        val index = count
        count += 1

        siteSet += url
        siteList += ShortSiteRow(index, url)

        ZIO.succeed(
          ShortSite(
            id = index2id(index),
            url = url
          )
        )
    }

  override def get(id: String): IO[GetFailure, ShortSite] = {
    val index = id2index(id)

    if index < 0 || index >= count then ZIO.fail(GetFailure.NotFound)
    else
      val site = siteList(index)
      ZIO.succeed(
        ShortSite(
          id = id,
          url = site.url
        )
      )
  }
}
