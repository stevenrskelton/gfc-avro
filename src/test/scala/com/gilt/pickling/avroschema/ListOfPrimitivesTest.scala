package com.gilt.pickling.avroschema

import org.scalatest.{Assertions, FunSuite}
import com.gilt.pickling.TestObjs._
import scala.pickling._
import com.gilt.pickling.TestUtils._

class ListOfPrimitivesTest extends FunSuite with Assertions {

  test("Generate schema from a case class with a list of int field") {
    assert(fingerPrint("/avro/lists/ListOfInts.avsc") === fingerPrint(ListOfInts(List(123)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfInts.avsc") === fingerPrint(Schema(ListOfInts(List(123)))))
    assert(fingerPrint("/avro/lists/ListOfInts.avsc") === fingerPrint(Schema(classOf[ListOfInts])))
    assert(fingerPrint("/avro/lists/ListOfInts.avsc") === fingerPrint(Schema[ListOfInts]))
  }

  test("Generate schema from a case class with a list of long field") {
    assert(fingerPrint("/avro/lists/ListOfLongs.avsc") === fingerPrint(ListOfLongs(List(123L)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfLongs.avsc") === fingerPrint(Schema(ListOfLongs(List(123L)))))
    assert(fingerPrint("/avro/lists/ListOfLongs.avsc") === fingerPrint(Schema(classOf[ListOfLongs])))
    assert(fingerPrint("/avro/lists/ListOfLongs.avsc") === fingerPrint(Schema[ListOfLongs]))
  }

  test("Generate schema from a case class with a list of double field") {
    assert(fingerPrint("/avro/lists/ListOfDoubles.avsc") === fingerPrint(ListOfDoubles(List(123D)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfDoubles.avsc") === fingerPrint(Schema(ListOfDoubles(List(123D)))))
    assert(fingerPrint("/avro/lists/ListOfDoubles.avsc") === fingerPrint(Schema(classOf[ListOfDoubles])))
    assert(fingerPrint("/avro/lists/ListOfDoubles.avsc") === fingerPrint(Schema[ListOfDoubles]))
  }

  test("Generate schema from a case class with a list of float field") {
    assert(fingerPrint("/avro/lists/ListOfFloats.avsc") === fingerPrint(ListOfFloats(List(123F)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfFloats.avsc") === fingerPrint(Schema(ListOfFloats(List(123F)))))
    assert(fingerPrint("/avro/lists/ListOfFloats.avsc") === fingerPrint(Schema(classOf[ListOfFloats])))
    assert(fingerPrint("/avro/lists/ListOfFloats.avsc") === fingerPrint(Schema[ListOfFloats]))
  }

  test("Generate schema from a case class with a list of boolean field") {
    assert(fingerPrint("/avro/lists/ListOfBooleans.avsc") === fingerPrint(ListOfBooleans(List(false, true)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfBooleans.avsc") === fingerPrint(Schema(ListOfBooleans(List(false, true)))))
    assert(fingerPrint("/avro/lists/ListOfBooleans.avsc") === fingerPrint(Schema(classOf[ListOfBooleans])))
    assert(fingerPrint("/avro/lists/ListOfBooleans.avsc") === fingerPrint(Schema[ListOfBooleans]))
  }

  test("Generate schema from a case class with a list of string field") {
    assert(fingerPrint("/avro/lists/ListOfStrings.avsc") === fingerPrint(ListOfStrings(List("abc")).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfStrings.avsc") === fingerPrint(Schema(ListOfStrings(List("abc")))))
    assert(fingerPrint("/avro/lists/ListOfStrings.avsc") === fingerPrint(Schema(classOf[ListOfStrings])))
    assert(fingerPrint("/avro/lists/ListOfStrings.avsc") === fingerPrint(Schema[ListOfStrings]))
  }

  test("Generate schema from a case class with a list of char field") {
    assert(fingerPrint("/avro/lists/ListOfChars.avsc") === fingerPrint(ListOfChars(List('a')).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfChars.avsc") === fingerPrint(Schema(ListOfChars(List('a')))))
    assert(fingerPrint("/avro/lists/ListOfChars.avsc") === fingerPrint(Schema(classOf[ListOfChars])))
    assert(fingerPrint("/avro/lists/ListOfChars.avsc") === fingerPrint(Schema[ListOfChars]))
  }

  test("Generate schema from a case class with a list of byte field") {
    assert(fingerPrint("/avro/lists/ListOfBytes.avsc") === fingerPrint(ListOfBytes(List(1.toByte)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfBytes.avsc") === fingerPrint(Schema(ListOfBytes(List(1.toByte)))))
    assert(fingerPrint("/avro/lists/ListOfBytes.avsc") === fingerPrint(Schema(classOf[ListOfBytes])))
    assert(fingerPrint("/avro/lists/ListOfBytes.avsc") === fingerPrint(Schema[ListOfBytes]))
  }

  test("Generate schema from a case class with a list of short field") {
    assert(fingerPrint("/avro/lists/ListOfShorts.avsc") === fingerPrint(ListOfShorts(List(1.toShort)).pickle.value))
    assert(fingerPrint("/avro/lists/ListOfShorts.avsc") === fingerPrint(Schema(ListOfShorts(List(1.toShort)))))
    assert(fingerPrint("/avro/lists/ListOfShorts.avsc") === fingerPrint(Schema(classOf[ListOfShorts])))
    assert(fingerPrint("/avro/lists/ListOfShorts.avsc") === fingerPrint(Schema[ListOfShorts]))  }

  test("Generate schema from a case class with an empty list of int field") {
    assert(fingerPrint("/avro/lists/ListOfInts.avsc") === fingerPrint(ListOfInts(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of long field") {
    assert(fingerPrint("/avro/lists/ListOfLongs.avsc") === fingerPrint(ListOfLongs(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of double field") {
    assert(fingerPrint("/avro/lists/ListOfDoubles.avsc") === fingerPrint(ListOfDoubles(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of float field") {
    assert(fingerPrint("/avro/lists/ListOfFloats.avsc") === fingerPrint(ListOfFloats(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of boolean field") {
    assert(fingerPrint("/avro/lists/ListOfBooleans.avsc") === fingerPrint(ListOfBooleans(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of string field") {
    assert(fingerPrint("/avro/lists/ListOfStrings.avsc") === fingerPrint(ListOfStrings(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of char field") {
    assert(fingerPrint("/avro/lists/ListOfChars.avsc") === fingerPrint(ListOfChars(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of byte field") {
    assert(fingerPrint("/avro/lists/ListOfBytes.avsc") === fingerPrint(ListOfBytes(List()).pickle.value))
  }

  test("Generate schema from a case class with an empty list of short field") {
    assert(fingerPrint("/avro/lists/ListOfShorts.avsc") === fingerPrint(ListOfShorts(List()).pickle.value))
  }

}
