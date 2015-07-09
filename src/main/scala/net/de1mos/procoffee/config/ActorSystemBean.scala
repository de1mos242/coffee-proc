package net.de1mos.procoffee.config

import akka.actor.ActorSystem
import net.de1mos.procoffee.actors.routes.{UserRoute, ApiRouterActor}

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
  lazy val apiRouterActor = system.actorOf(ApiRouterActor.props(userRoute), "api-router")

}
