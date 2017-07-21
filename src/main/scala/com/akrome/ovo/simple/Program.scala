package com.akrome.ovo.simple

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit.DAYS

import com.akrome.ovo.Types.AddressBookEntry.{DateOfBirth, FullName, Gender}
import com.akrome.ovo.Types.{AbstractGender, AddressBookEntry}
import com.akrome.ovo.shapeless.Program.args

import scala.io.Source

object Program extends App {
  val lines = Source.fromFile(args(0)).getLines.toList

  val splitLines = lines.map(_.split("\\s*,\\s*"))

  val dateOfBirthFormatter = new DateTimeFormatterBuilder()
    .appendPattern("d/MM/")
    .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.of(1900, 1, 1))
    .toFormatter()

  val addressBookEntries = splitLines.map(l => AddressBookEntry(
    FullName(l(0)),
    Gender(AbstractGender.withName(l(1))),
    DateOfBirth(LocalDate.parse(l(2), dateOfBirthFormatter))
  ))

  val numberOfMales = addressBookEntries.count(_.gender.value == AbstractGender.MALE)
  println(s"1. $numberOfMales")

  val oldestPerson = addressBookEntries.minBy(_.dateOfBirth.value.toEpochDay)
  println(s"2. $oldestPerson")

  val bill = addressBookEntries.find(_.fullName.value.startsWith("Bill")).get
  val paul = addressBookEntries.find(_.fullName.value.startsWith("Paul")).get
  val daysBillIsOlderThanPaul = DAYS.between(bill.dateOfBirth.value, paul.dateOfBirth.value)
  println(s"3. $daysBillIsOlderThanPaul")
}
