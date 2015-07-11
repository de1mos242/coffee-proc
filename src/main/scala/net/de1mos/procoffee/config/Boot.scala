package net.de1mos.procoffee.config

import akka.io.IO
import spray.can.Http

/**
 * Created by Unknown on 06.07.2015.
 */
object Boot extends App {

  val services = ActorSystemBean()
  implicit val system = services.system
  val service = services.apiRouterActor

  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8088)

}
