package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.Purchase

/**
 * Created by De1mos on 13.07.2015.
 */

object PurchasesService {
  def apply() = new PurchasesService();
}

class PurchasesService extends CrudService[Purchase] {

  import Purchases.testPurchases

  override def findById(id: Long): Option[Purchase] = testPurchases.find(_.id == Some(id))

  override def update(entityId : Long, entity: Purchase) = {
    testPurchases.indexWhere(_.id == Some(entityId)) match {
      case -1 => throw new IllegalStateException(s"Purchase with id: ${entityId} not found")
      case i => testPurchases.update(i, entity); getById(entityId)
    }
  }

  override def delete(entityId: Long) = {
    findById(entityId) match {
      case Some(purchase) => testPurchases -= purchase
      case None =>
    }
  }

  override def add(entity: Purchase) = {
    val maxId = testPurchases.map(_.id).flatten.max + 1
    val user = UserService().save(entity.user)
    var newPurchase = entity.copy(id = Some(maxId), user = user)
    testPurchases += newPurchase
    newPurchase
  }

  override def getAll() = testPurchases.toList
}
