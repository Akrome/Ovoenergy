package com.akrome.ovo.shapeless

import java.time.LocalDate

import com.akrome.ovo.Types.{AbstractGender, AddressBookEntry}
import org.scalatest.FunSuite

class CsvFileReaderTest extends FunSuite {
  test("A CsvFileReader[AddressBookEntry] should parse successfully correctly csvFiles") {
    val (errors, entries) = CsvFileReader.readLinesAs("TestAddressBook.csv")
    errors match {
      case List(a, b, c, d)
        if  a == List("Full name cannot be empty") &&
            b == List("No value found for 'U'") &&
            c.size == 1 && c.head.contains("could not be parsed") &&
            d == List("Full name cannot be empty","No value found for 'F'") =>
      case x => fail(s"Wrong result for CsvFileReader[AddressBookEntry]: $x")
    }

    assertResult(List(
      AddressBookEntry(
        AddressBookEntry.FullName("Wes Jackson"),
        AddressBookEntry.Gender(AbstractGender.MALE),
        AddressBookEntry.DateOfBirth(LocalDate.of(1974, 8, 14))
      ))) {entries}
  }
}
