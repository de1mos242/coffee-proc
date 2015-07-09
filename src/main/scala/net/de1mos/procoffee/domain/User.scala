package net.de1mos.procoffee.domain

import spray.json.DefaultJsonProtocol

/**
 * Created by Unknown on 06.07.2015.
 */
case class User(id: Option[Long], firstName: String, lastName: String)

object UserJsonProtocol extends DefaultJsonProtocol{
  implicit val userFormat = jsonFormat3(User)
}
