package net.de1mos.procoffee.actors.routes

import akka.actor.{Actor, Props}
import net.de1mos.procoffee.domain.Purchase
import net.de1mos.procoffee.services.PurchasesService
import net.de1mos.procoffee.transforms.ToJson
import net.de1mos.procoffee.transforms.ToJson._
import spray.json.JsValue

/**
 * Created by De1mos on 13.07.2015.
 */
object PurchaseRoute {
  def props: Props = Props(new PurchaseRoute())
}

class PurchaseRoute extends Actor with PurchaseRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(purchaseRoute)
}

trait PurchaseRouteTrait extends CrudRoute[Purchase, PurchasesService] {
  override def crudService = PurchasesService()
  override def getRequestUnmarshaller()= as[Purchase]
  override def convertToJson(entity : Purchase) : JsValue = ToJson.purchaseFormat.write(entity)
  val purchaseRoute = crudRoutes
}