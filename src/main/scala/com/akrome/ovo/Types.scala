package com.akrome.ovo

import java.time.LocalDate

import com.akrome.ovo.Types.AbstractGender.AbstractGender

object Types {

  case class AddressBookEntry(fullName: AddressBookEntry.FullName, gender: AddressBookEntry.Gender, dateOfBirth: AddressBookEntry.DateOfBirth)
  object AddressBookEntry {
    case class FullName(value: String) extends AnyVal
    case class Gender(value: AbstractGender) extends AnyVal
    case class DateOfBirth(value: LocalDate) extends AnyVal
  }


  //NOTE: This is *NOT* case insensitive!
  object AbstractGender extends Enumeration {
    type AbstractGender = Value
    val MALE = Value("Male")
    val FEMALE = Value("Female")
  }

  case class A(i: AddressBookEntry.FullName)
}
