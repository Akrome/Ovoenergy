package com.akrome.ovo.shapeless

import java.time.LocalDate

import com.akrome.ovo.Types.{AbstractGender, AddressBookEntry}
import org.scalatest.FunSuite

class LineParserTest extends FunSuite {
  test("A LineParser[AddressBookEntry] should parse successfully valid strings") {
    val lineUnderTest = List("Ajeje Brazor","Male","09/03/89")
    LineParser[AddressBookEntry](lineUnderTest) match {
      case Right(AddressBookEntry(
        AddressBookEntry.FullName("Ajeje Brazor"),
        AddressBookEntry.Gender(AbstractGender.MALE),
        AddressBookEntry.DateOfBirth(date)
      )) if date.equals(LocalDate.of(1989, 3, 9))=>
      case x => fail(s"Wrong result for LineParser[AddressBookEntry]: $x")
    }
  }

  test("A LineParser[AddressBookEntry] should return the correct number of errors") {
    val lineUnderTest = List("","Unknown","09/13/89")
    LineParser[AddressBookEntry](lineUnderTest) match {
      case Left(List(a, b, c))
        if  a == "Full name cannot be empty" &&
            b == "No value found for 'Unknown'" &&
            c.contains("could not be parsed") =>
      case x => fail(s"Wrong result for LineParser[AddressBookEntry]: $x")
    }
  }
}
