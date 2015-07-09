package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.User

/**
 * Created by Unknown on 06.07.2015.
 */

object UserService {
  def apply() = new UserService
}

class UserService {
  import UserData.testUsers

  def getUsers = testUsers.toList

  def getUserById(userId: Long) = testUsers.find(_.id == Some(userId))

  def addUser(user: User) = {
    val maxId = testUsers.map(_.id).flatten.max + 1
    val newUser = user.copy(id = Some(maxId))
    testUsers += newUser
    newUser
  }

  def updateUser(user: User) = {
    testUsers.indexWhere(_.id == user.id) match {
      case -1 => false
      case i => testUsers.update(i, user); true
    }
  }

  def deleteUser(userId: Long) = {
    getUserById(userId) match {
      case Some(user) => testUsers -= user
      case None =>
    }
  }

}
