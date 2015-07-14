package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.UserActivity

/**
 * Created by De1mos on 15.07.2015.
 */
class UserActivityService extends CrudService[UserActivity] {
  import UserAcitivities.userActivities

  def getByUserId(userId: Long) : List[UserActivity] = userActivities.filter(_.user.id == Some(userId)).toList

  override def findById(id: Long): Option[UserActivity] = userActivities.find(_.id == Some(id))

  override def update(entityId: Long, entity: UserActivity): UserActivity = {
    userActivities.indexWhere(_.id == Some(entityId)) match {
      case -1 => throw new IllegalStateException(s"User acitivity with id: ${entityId} not found")
      case i => userActivities.update(i, entity); getById(entityId)
    }
  }

  override def delete(entityId: Long): Unit = {
    findById(entityId) match {
      case Some(purchase) => userActivities -= purchase
      case None =>
    }
  }

  override def add(entity: UserActivity): UserActivity = {
    val maxId = userActivities.map(_.id).flatten.max + 1
    val user = UserService().save(entity.user)
    var newEntity = entity.copy(id = Some(maxId), user = user)
    userActivities += newEntity
    newEntity
  }

  override def getAll(): List[UserActivity] = userActivities.toList
}

object UserActivityService {
  def apply() = new UserActivityService()
}
