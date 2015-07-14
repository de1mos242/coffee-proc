package net.de1mos.procoffee.config

import akka.actor.ActorSystem
import net.de1mos.procoffee.actors.routes.{UserActivityRoute, PurchaseRoute, UserRoute, ApiRouterActor}

/**
* Factory method for ActorSystemBean class
*/
object ActorSystemBean {
  def apply(): ActorSystemBean = new ActorSystemBean()
}

/**
 * Defines an actor system with the actors used by
 * the spray-person application
 */
class ActorSystemBean {

  implicit val system = ActorSystem("procoffee")

  //lazy val personRoute = system.actorOf(PersonRoute.props, "person-route")
  lazy val userRoute = system.actorOf(UserRoute.props, "user-route")
  lazy val userActivityRoute = system.actorOf(UserActivityRoute.props, "user-activity-route")
  lazy val purchaseRoute = system.actorOf(PurchaseRoute.props, "purchase-route")
  lazy val routes = ApiRouterActor.props(userRoute, purchaseRoute, userActivityRoute)
  lazy val apiRouterActor = system.actorOf(routes, "api-router")

}
