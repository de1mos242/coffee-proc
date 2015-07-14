package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.User

/**
 * Created by Unknown on 06.07.2015.
 */

object UserService {
  def apply() = new UserService
}

class UserService extends CrudService[User]{
  import UserData.testUsers

  override def getAll = testUsers.toList

  override def findById(userId: Long) = testUsers.find(_.id == Some(userId))

  override def add(user: User) = {
    val maxId = testUsers.map(_.id).flatten.max + 1
    val newUser = user.copy(id = Some(maxId))
    testUsers += newUser
    newUser
  }

  override def update(entityId : Long, user: User) = {
    testUsers.indexWhere(_.id == Some(entityId)) match {
      case -1 => throw new IllegalStateException(s"User with id: ${entityId} not found")
      case i => testUsers.update(i, user); getById(entityId)
    }
  }

  override def delete(userId: Long) = {
    findById(userId) match {
      case Some(user) => testUsers -= user
      case None =>
    }
  }
}
