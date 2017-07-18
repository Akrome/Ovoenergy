package com.akrome.ovo.shapeless

import java.time.temporal.ChronoUnit.DAYS

import com.akrome.ovo.Types.AbstractGender

object Program extends App {
  val (errors, addressBookEntries) = CsvFileReader.readLinesAs(args(0))

  println(s"Errors: $errors")

  val numberOfMales = addressBookEntries.count(_.gender.value == AbstractGender.MALE)
  println(s"Number of males: $numberOfMales")

  val oldestPerson = addressBookEntries.minBy(_.dateOfBirth.value.toEpochDay)
  println(s"Oldest entry by date of birth: $oldestPerson")

  val bill = addressBookEntries.find(_.fullName.value.startsWith("Bill")).get
  val paul = addressBookEntries.find(_.fullName.value.startsWith("Paul")).get
  val daysBillIsOlderThanPaul = DAYS.between(bill.dateOfBirth.value, paul.dateOfBirth.value)
  println(s"Bill is $daysBillIsOlderThanPaul days older than Paul")
}
