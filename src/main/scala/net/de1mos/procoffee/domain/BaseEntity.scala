package net.de1mos.procoffee.domain

import spray.json.DefaultJsonProtocol

/**
 * Created by De1mos on 13.07.2015.
 */
trait BaseEntity {
  val id : Option[Long]
}
