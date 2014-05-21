package com.gilt.pickling.avro

import scala.pickling._
import scala.pickling.PicklingException
import scala.collection.mutable
import scala.reflect.runtime.universe._

final class AvroPickleBuilder(format: AvroPickleFormat, buffer: AvroEncodingOutput = new AvroEncodingOutput()) extends PBuilder with PickleTools {

  import com.gilt.pickling.util.Types._

  private val tags = new mutable.Stack[Type]()

  @inline def beginEntry(picklee: Any): PBuilder = withHints {
    hints =>
      hints.tag.key match {
        case KEY_INT if isNotRootObject => buffer.encodeIntTo(picklee.asInstanceOf[Int])
        case KEY_LONG if isNotRootObject => buffer.encodeLongTo(picklee.asInstanceOf[Long])
        case KEY_FLOAT if isNotRootObject => buffer.encodeFloatTo(picklee.asInstanceOf[Float])
        case KEY_DOUBLE if isNotRootObject => buffer.encodeDoubleTo(picklee.asInstanceOf[Double])
        case KEY_BOOLEAN if isNotRootObject => buffer.encodeBooleanTo(picklee.asInstanceOf[Boolean])
        case KEY_SCALA_STRING | KEY_JAVA_STRING if isNotRootObject => buffer.encodeStringTo(picklee.asInstanceOf[String])
        case KEY_BYTE if isNotRootObject => buffer.encodeByteTo(picklee.asInstanceOf[Byte])
        case KEY_SHORT if isNotRootObject => buffer.encodeShortTo(picklee.asInstanceOf[Short])
        case KEY_CHAR if isNotRootObject => buffer.encodeCharTo(picklee.asInstanceOf[Char])
        case KEY_ARRAY_INT if parentIsACaseClass => buffer.encodeIntArrayTo(picklee.asInstanceOf[Array[Int]])
        case KEY_ARRAY_LONG if parentIsACaseClass => buffer.encodeLongArrayTo(picklee.asInstanceOf[Array[Long]])
        case KEY_ARRAY_FLOAT if parentIsACaseClass => buffer.encodeFloatArrayTo(picklee.asInstanceOf[Array[Float]])
        case KEY_ARRAY_DOUBLE if parentIsACaseClass => buffer.encodeDoubleArrayTo(picklee.asInstanceOf[Array[Double]])
        case KEY_ARRAY_BOOLEAN if parentIsACaseClass => buffer.encodeBooleanArrayTo(picklee.asInstanceOf[Array[Boolean]])
        case KEY_ARRAY_BYTE if parentIsACaseClass => buffer.encodeByteArrayTo(picklee.asInstanceOf[Array[Byte]])
        case KEY_ARRAY_SHORT if parentIsACaseClass => buffer.encodeShortArrayTo(picklee.asInstanceOf[Array[Short]])
        case KEY_ARRAY_CHAR if parentIsACaseClass => buffer.encodeCharArrayTo(picklee.asInstanceOf[Array[Char]])
        case KEY_UUID if parentIsACaseClass => //Nothing to do. Wait for bytes field.
        case KEY_ARRAY_BYTE if tags.head <:< uuidType => buffer.encodeFixedByteArrayTo(picklee.asInstanceOf[Array[Byte]])
        case KEY_NIL if parentIsACaseClass => buffer.encodeByteArrayTo(Array.empty)
        case KEY_NONE if parentIsACaseClass => buffer.encodeLongTo(0)
        case KEY_UNIT | KEY_NULL => throw new PicklingException("Not supported.")
        case _ =>
          hints.tag.tpe match {
            case tpe if tpe <:< someType && parentIsACaseClass => buffer.encodeLongTo(1)
            case tpe if isSupportedCollectionType(tpe) && parentIsACaseClass =>
            case tpe@TypeRef(_, s: ClassSymbol, _) if s.isCaseClass && !(tpe <:< iterableType) =>
            case _ => throw new PicklingException(s"${hints.tag.key} is not supported.")
          }
      }
      tags.push(hints.tag.tpe)
      this
  }

  @inline def putField(name: String, pickler: PBuilder => Unit): PBuilder = {
    pickler(this)
    this
  }

  @inline def endEntry(): Unit = tags.pop()

  @inline def beginCollection(length: Int): PBuilder = {
    writeStartOfCollection()
    buffer.encoder.setItemCount(length)
    this
  }

  @inline def putElement(pickler: PBuilder => Unit): PBuilder = {
    buffer.encoder.startItem()
    pickler(this)
    this
  }

  @inline def endCollection(): Unit =
    tags.top match {
      case tpe if tpe <:< mapType => buffer.encoder.writeMapEnd()
      case _ => buffer.encoder.writeArrayEnd()
    }

  @inline def result() = AvroPickle(buffer.result())

  private def isSupportedCollectionType(tpe: Type): Boolean = tpe <:< mapType || tpe <:< setType || tpe <:< seqType || tpe <:< arrayType

  private def isNotRootObject: Boolean = tags.length > 0

  private def writeStartOfCollection() =
    tags.top match {
      case tpe if tpe <:< mapType => buffer.encoder.writeMapStart()
      case _ => buffer.encoder.writeArrayStart()
    }

  private def parentIsACaseClass: Boolean =
    tags.elems match {
      case TypeRef(_, s: ClassSymbol, _) :: tail if s.isCaseClass => true
      case _ => false
    }
}
