package com.akrome.ovo.shapeless

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

import com.akrome.ovo.Types.{AbstractGender, AddressBookEntry}

import scala.collection.mutable.ListBuffer

trait Parser[T] {
  def parse(s:String): Either[List[String], T]
}

object Parser {
  def apply[A](implicit parser:Parser[A]): Parser[A] = parser

  implicit object addressBookEntryFullNameParser extends Parser[AddressBookEntry.FullName] {
    override def parse(s: String): Either[List[String], AddressBookEntry.FullName] = {
      if (s.isEmpty) {
        Left(List("Full name cannot be empty"))
      }
      else {
        Right(AddressBookEntry.FullName(s))
      }
    }
  }

  implicit object addressBookEntryGenderParser extends Parser[AddressBookEntry.Gender] {
    override def parse(s: String): Either[List[String], AddressBookEntry.Gender] = {
      if (s.isEmpty) {
        Left(List("Gender cannot be empty"))
      }
      else {
        val errors = ListBuffer[String]()
        val abstractGender =
          try {
            AbstractGender.withName(s)
          }
          catch {
            case t: Throwable => {
              errors += t.getMessage
              null
            }
          }
        if (errors.isEmpty) {
          Right(AddressBookEntry.Gender(abstractGender))
        }
        else {
          Left(errors.toList)
        }
      }
    }
  }

  val dateOfBirthFormatter = new DateTimeFormatterBuilder()
    .appendPattern("d/MM/")
    .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.of(1900, 1, 1))
    .toFormatter();
  implicit object addressBookEntryDateOfBirthParser extends Parser[AddressBookEntry.DateOfBirth] {
    override def parse(s: String): Either[List[String], AddressBookEntry.DateOfBirth] = {
      if (s.isEmpty) {
        Left(List("Date of birth cannot be empty"))
      }
      else {
        val errors = ListBuffer[String]()
        val dateOfBirth =
          try {
            LocalDate.parse(s, dateOfBirthFormatter)
          }
          catch {
            case t: Throwable => {
              errors += t.getMessage
              null
            }
          }
        if (errors.isEmpty) {
          Right(AddressBookEntry.DateOfBirth(dateOfBirth))
        }
        else {
          Left(errors.toList)
        }
      }
    }
  }
}


