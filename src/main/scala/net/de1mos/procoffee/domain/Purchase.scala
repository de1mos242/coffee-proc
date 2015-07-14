package net.de1mos.procoffee.domain

import java.time.LocalDate

/**
 * Created by De1mos on 13.07.2015.
 */
case class Purchase (id : Option[Long], user : User, cost : BigDecimal, date : LocalDate) extends BaseEntity




