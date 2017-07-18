package com.akrome.ovo.shapeless

import java.time.LocalDate

import com.akrome.ovo.Types.{AbstractGender, AddressBookEntry}
import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  /*
  *** FULL NAME ***
   */
  test("A FullNameParser should parse successfully non-empty strings") {
    val stringUnderTest = "Ajeje Brazor"
    Parser[AddressBookEntry.FullName].parse(stringUnderTest) match {
      case Right(AddressBookEntry.FullName(s)) if s == stringUnderTest =>
      case x => fail(s"Wrong result for FullNameParser: $x")
    }
  }

  test("A FullNameParser should fail to parse empty strings") {
    val stringUnderTest = ""
    Parser[AddressBookEntry.FullName].parse(stringUnderTest) match {
      case Left(List("Full name cannot be empty")) =>
      case x => fail(s"Wrong result for FullNameParser: $x")
    }
  }

  /*
  *** GENDER ***
   */
  test("A GenderParser should parse successfully the 'Male' string") {
    val stringUnderTest = "Male"
    Parser[AddressBookEntry.Gender].parse(stringUnderTest) match {
      case Right(AddressBookEntry.Gender(AbstractGender.MALE)) =>
      case x => fail(s"Wrong result for GenderParser: $x")
    }
  }

  test("A GenderParser should parse successfully the 'Female' string") {
    val stringUnderTest = "Female"
    Parser[AddressBookEntry.Gender].parse(stringUnderTest) match {
      case Right(AddressBookEntry.Gender(AbstractGender.FEMALE)) =>
      case x => fail(s"Wrong result for GenderParser: $x")
    }
  }

  test("A GenderParser should fail to parse unknown strings") {
    val stringUnderTest = "Unknown"
    Parser[AddressBookEntry.Gender].parse(stringUnderTest) match {
      case Left(List(e)) if e startsWith s"No value found for '$stringUnderTest'" =>
      case x => fail(s"Wrong result for GenderParser: $x")
    }
  }

  test("A GenderParser should fail to parse empty strings") {
    val stringUnderTest = ""
    Parser[AddressBookEntry.Gender].parse(stringUnderTest) match {
      case Left(List("Gender cannot be empty")) =>
      case x => fail(s"Wrong result for GenderParser: $x")
    }
  }

  /*
  *** DATE OF BIRTH ***
  */
  test("A DateOfBirthParser should parse successfully well-formatted strings") {
    val stringUnderTest = "12/03/95"
    Parser[AddressBookEntry.DateOfBirth].parse(stringUnderTest) match {
      case Right(AddressBookEntry.DateOfBirth(d)) if d == LocalDate.of(1995, 3, 12) =>
      case x => fail(s"Wrong result for DateOfBirthParser: $x")
    }
  }

  test("A DateOfBirthParser should fail to parse ill-formatted strings") {
    val stringUnderTest = "12/13/2015"
    Parser[AddressBookEntry.DateOfBirth].parse(stringUnderTest) match {
      case Left(List(e)) if e.startsWith(s"Text '$stringUnderTest' could not be parsed") =>
      case x => fail(s"Wrong result for DateOfBirthParser: $x")
    }
  }

  test("A DateOfBirthParser should fail to parse empty strings") {
    val stringUnderTest = ""
      Parser[AddressBookEntry.DateOfBirth].parse(stringUnderTest) match {
        case Left(List("Date of birth cannot be empty")) =>
        case x => fail(s"Wrong result for DateOfBirthParser: $x")
      }
  }
}
