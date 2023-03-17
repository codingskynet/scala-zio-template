package dev.zero_cost.scala_zio
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.should.Matchers
import zio.test.*
import zio.test.Assertion.*

import scala.collection.mutable
import scala.util.Random

object InMemoryShortSiteRepositoryTest extends ZIOSpecDefault {
  private val repo =
    new InMemoryShortSiteRepository(Set.empty, mutable.ArrayBuffer.empty, 0)

  def spec: Spec[Any, Any] = suite("InMemoryShortSiteRepository") {
    test("create success") {
      val url = Random.nextString(10)

      repo.create(url).flatMap { result =>
        assertTrue(result.url == url)
      }
    }
    test("get success") {
      val url = Random.nextString(10)

      for {
        shortSite <- repo.create(url)
        result <- repo.get(shortSite.id)
      } yield assertTrue(result.url == shortSite.url)
    }
    test("create already exists") {
      val url = Random.nextString(10)

      for {
        _ <- repo.create(url)
        result <- repo.create(url).exit
      } yield assert(result)(
        fails(equalTo(ShortSiteRepository.CreateFailure.Exists))
      )
    }
    test("get not found") {
      val id = Random.nextString(10)

      for {
        result <- repo.get(id).exit
      } yield assert(result)(
        fails(equalTo(ShortSiteRepository.GetFailure.NotFound))
      )
    }
  }
}
