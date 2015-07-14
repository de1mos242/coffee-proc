package net.de1mos.procoffee.services

import java.text.DateFormat
import java.time.{Month, LocalDate, LocalDateTime}
import java.util.Date

import net.de1mos.procoffee.domain.{Purchase, User}

import scala.collection.mutable.ArrayBuffer

/**
 * Created by De1mos on 13.07.2015.
 */
object Purchases {
  val userService = UserService()
  var testPurchases = ArrayBuffer(
    Purchase(Some(1), userService.findById(1).get, 440, LocalDate.of(2015, Month.JUNE, 9)),
    Purchase(Some(2), userService.findById(2).get, 140, LocalDate.of(2015, Month.JULY, 10)),
    Purchase(Some(3), userService.findById(4).get, 220, LocalDate.of(2015, Month.JULY, 10)),
    Purchase(Some(4), userService.findById(2).get, 500, LocalDate.of(2015, Month.JULY, 12))
  )
}
