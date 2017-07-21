package com.akrome.ovo.shapeless

import java.time.temporal.ChronoUnit.DAYS

import com.akrome.ovo.Types.AbstractGender

object Program extends App {
  val (errors, addressBookEntries) = CsvFileReader.readLinesAs(args(0))

  val numberOfMales = addressBookEntries.count(_.gender.value == AbstractGender.MALE)
  println(s"1. $numberOfMales")

  val oldestPerson = addressBookEntries.minBy(_.dateOfBirth.value.toEpochDay)
  println(s"2. $oldestPerson")

  val bill = addressBookEntries.find(_.fullName.value.startsWith("Bill")).get
  val paul = addressBookEntries.find(_.fullName.value.startsWith("Paul")).get
  val daysBillIsOlderThanPaul = DAYS.between(bill.dateOfBirth.value, paul.dateOfBirth.value)
  println(s"3. $daysBillIsOlderThanPaul")
}
