package dev.zero_cost.scala_zio
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class CipherTest extends AnyFunSpecLike with Matchers {
  it("should encrypt and decrypt well") {
    (1 to 10_000).foreach { _ =>
      val randomNum = Random.nextInt()
      decrypt(encrypt(randomNum)) shouldBe randomNum
    }
  }

  it("should convert between positive num and string") {
    (1 to 10_000).foreach { _ =>
      val randomNum = Math.abs(Random.nextLong())
      string2Long(long2String(randomNum)) shouldBe randomNum
    }
  }

  it("should convert between index and id") {
    (1 to 10_000).foreach { _ =>
      val randomIdx = Random.nextInt()
      id2index(index2id(randomIdx)) shouldBe randomIdx
    }
  }
}
