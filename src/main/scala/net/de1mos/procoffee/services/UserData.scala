package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.User

import scala.collection.mutable.ArrayBuffer

/**
 * Created by Unknown on 06.07.2015.
 */
object UserData {
  var testUsers = ArrayBuffer(
    User(Some(1), "Bill", "Smith"),
    User(Some(2), "John", "Doe"),
    User(Some(3), "Steven", "Gangstead"),
    User(Some(4), "Andrew", "Rubalcaba"))
}
