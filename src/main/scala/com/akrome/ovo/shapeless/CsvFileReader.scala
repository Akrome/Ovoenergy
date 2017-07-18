package com.akrome.ovo.shapeless

import com.akrome.ovo.Types.AddressBookEntry

import scala.io.Source

object CsvFileReader {
  def readLinesAs(fileName: String): (List[List[String]], List[AddressBookEntry]) = {
    val lines = Source.fromFile(fileName).getLines.toList
    val splitLines = lines.map(l => l.split("\\s*,\\s*").toList)
    val eitherEntries = splitLines.map(row => LineParser[AddressBookEntry](row))
    val (lefts, rights) = eitherEntries.partition(_.isLeft)
    val ts = rights.map(_.right.get)
    val errors = lefts.map(_.left.get)
    (errors, ts)
  }
}
