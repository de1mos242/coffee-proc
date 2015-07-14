package net.de1mos.procoffee.actors.routes

import akka.actor.{Actor, Props}
import net.de1mos.procoffee.domain.User
import net.de1mos.procoffee.services.UserService
import net.de1mos.procoffee.transforms.ToJson
import net.de1mos.procoffee.transforms.ToJson._
import spray.json.JsValue

/**
 * Factory method for Props configuration files for actors
 */
object UserRoute {
  def props: Props = Props(new UserRoute())
}

class UserRoute() extends Actor with UserRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(userRoute)
}

/**
 * Separate routing logic in an HttpService trait so that the
 * routing logic can be tested outside of an actor system in specs/mockito tests
 */
trait UserRouteTrait extends CrudRoute[User, UserService] {

  override def crudService = UserService()
  override def getRequestUnmarshaller()= as[User]
  override def convertToJson(entity : User) : JsValue = ToJson.userFormat.write(entity)
  val userRoute = crudRoutes
}
