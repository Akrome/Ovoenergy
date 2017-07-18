package com.akrome.ovo.shapeless

import shapeless._

trait LineParser[T] {
  def parse(l: List[String]): Either[List[String], T]
}

object LineParser {
  implicit val hnilParser: LineParser[HNil] = {
    case Nil => Right(HNil)
    case h +: t => Left(List(s"Expected end of line, got '$h'"))
  }

  implicit def hconsParser[H : Parser, T <: HList : LineParser]: LineParser[H :: T] =
    (s: List[String]) => s match {
      case Nil => Left(List("Excepted list element."))
      case h +: t => {
        val head = implicitly[Parser[H]].parse(h)
        val tail = implicitly[LineParser[T]].parse(t)
        (head, tail) match {
          case (Left(headErrors), Left(tailErrors)) => Left(headErrors ++ tailErrors)
          case (Left(headErrors), Right(_)) => Left(headErrors)
          case (Right(_), Left(tailErrors)) => Left(tailErrors)
          case (Right(hh), Right(tt)) => Right(hh :: tt)
        }
      }
    }

  implicit def caseClassParser[T, R <: HList](implicit gen: Generic[T] { type Repr = R },
                                              reprParser: LineParser[R]): LineParser[T] =
    (s: List[String]) => reprParser.parse(s).right.map(gen.from)

  def apply[A](s: List[String])(implicit parser: LineParser[A]):  Either[List[String], A] = parser.parse(s)
}
