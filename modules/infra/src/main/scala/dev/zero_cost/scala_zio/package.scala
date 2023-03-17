package dev.zero_cost

package object scala_zio {
  /*
   * For make pseudo-random string, not using hash for zero collision, use feistel cipher
   * https://en.wikipedia.org/wiki/Feistel_cipher
   */

  private val digits =
    "abcdefghjkmnpqrstuvwxyz123456789ABCEFGHJKLMNPRSTWXYZ"
  private val base = digits.length.toLong

  private def round(n: Int, r: Int): Int =
    (2.14 + (n.toDouble - r) * 314.15926535 / 27.182818284).toInt + r

  def encrypt(plain: Int): Int = {
    val half = (1 << 16) - 1

    var l1 = (plain >> 16) & half
    var r1 = plain & half
    var l2 = 0
    var r2 = 0

    (1 to 5).foreach { r =>
      l2 = r1
      r2 = l1 ^ (round(r1, r) & half)
      l1 = l2
      r1 = r2
    }

    (r1 << 16) | l1
  }

  def decrypt(cipher: Int): Int = {
    val half = (1 << 16) - 1

    var l1 = cipher & half
    var r1 = (cipher >> 16) & half
    var l2 = 0
    var r2 = 0

    (1 to 5).reverse.foreach { r =>
      r2 = l1
      l2 = r1 ^ (round(l1, r) & half)
      r1 = r2
      l1 = l2
    }

    (l1 << 16) | r1
  }

  def long2String(n: Long): String = {
    var num = n
    var result = ""

    while num > 0
    do {
      result += digits((num % base).toInt)
      num /= base
    }

    result
  }

  def string2Long(str: String): Long = {
    var b = 1L
    var result = 0L

    for c <- str
    do {
      result += digits.indexOf(c) * b
      b *= base
    }

    result
  }

  def index2id(index: Int): String =
    long2String(Integer.toUnsignedLong(encrypt(index)))

  def id2index(id: String): Int =
    decrypt(string2Long(id).toInt)
}
