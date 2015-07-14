package net.de1mos.procoffee.domain

import java.time.LocalDate

/**
 * Created by De1mos on 15.07.2015.
 */
case class UserActivity(id : Option[Long], user : User, activityStart : LocalDate, activityFinish : LocalDate) extends BaseEntity
