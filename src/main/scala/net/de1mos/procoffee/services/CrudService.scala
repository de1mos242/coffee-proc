package net.de1mos.procoffee.services

import net.de1mos.procoffee.domain.BaseEntity

/**
 * Created by De1mos on 13.07.2015.
 */
trait CrudService[EntityClass <: BaseEntity] {

  def findById(id : Long) : Option[EntityClass]

  def getById(id : Long) : EntityClass = {
    findById(id).get
  }

  def save(entity : EntityClass) : EntityClass = {
    entity.id match {
      case Some(id) => update(id, entity)
      case None => add(entity)
    }
  }

  def add(entity : EntityClass) : EntityClass

  def update(entityId : Long,  entity : EntityClass) : EntityClass

  def delete(entityId : Long)

  def getAll() : List[EntityClass]
}
