package net.de1mos.procoffee.services

import java.time.{Month, LocalDate}

import net.de1mos.procoffee.domain.UserActivity

import scala.collection.mutable.ArrayBuffer

/**
 * Created by De1mos on 15.07.2015.
 */
object UserAcitivities {
  val userService = UserService()
  var userActivities = ArrayBuffer(
    UserActivity(id = Some(1), user = userService.getById(1), activityStart = LocalDate.parse("2015-06-01"), activityFinish = LocalDate.parse("2015-08-12")),
    UserActivity(id = Some(2), user = userService.getById(2), activityStart = LocalDate.parse("2015-05-01"), activityFinish = LocalDate.parse("2015-05-12")),
    UserActivity(id = Some(3), user = userService.getById(2), activityStart = LocalDate.parse("2015-06-02"), activityFinish = LocalDate.parse("2015-08-12")),
    UserActivity(id = Some(4), user = userService.getById(3), activityStart = LocalDate.parse("2015-06-01"), activityFinish = LocalDate.parse("2015-07-12"))
  )
}
