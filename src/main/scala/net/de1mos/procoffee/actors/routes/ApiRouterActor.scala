package net.de1mos.procoffee.actors.routes

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.Logging
import spray.http.{HttpResponse, HttpRequest}
import spray.routing.{Rejected, HttpService}
import spray.routing.directives.LogEntry

/**
 * Factory method for Props configuration files for actors
 */
object ApiRouterActor {
  def props(userRoute: ActorRef, purchaseRoute: ActorRef, userActivityRoute: ActorRef): Props =
    Props(new ApiRouterActor(userRoute, purchaseRoute, userActivityRoute))
}

/**
 * Routes the incoming request.  If the route begins with "api" the request is passed
 * along to the matching spray routing actor (if there's a match)
 *
 * Other routes are assumed to be static resources and are served from the resource
 * directory on the classpath.  getFromResourceDirectory takes the remainder of the path
 * so a route like "index.html" is completed with the classpath resource "dist/index.html"
 * or returns a 404 if it's not found.
 *
 * To run the front end app in dev mode change "dist" to "app"
 */
class ApiRouterActor(userRoute: ActorRef, purchaseRoute: ActorRef, userActivityRoute: ActorRef)
  extends Actor with HttpService with ActorLogging with UnhandledRouteDebug{

  def actorRefFactory = context
  def receive = runRoute {
    val staticPath = "app"
    val indexLocation = s"${staticPath}/index.html"
    compressResponseIfRequested() {
      pathPrefix("api") {
        pathPrefix("user-activities") { ctx => userActivityRoute ! ctx }
      } ~
      pathPrefix("api") {
        pathPrefix("user") { ctx => userRoute ! ctx }
      } ~
      pathPrefix("api") {
        pathPrefix("purchase") { ctx => purchaseRoute ! ctx }
      } ~
      pathPrefix("users") {
        getFromFile(indexLocation)
      } ~
      pathPrefix("user-activities") {
        getFromFile(indexLocation)
      } ~
      pathPrefix("purchases") {
        getFromFile(indexLocation)
      } ~
      pathEnd {
        getFromFile(indexLocation)
      } ~
        //getFromResourceDirectory("dist")
      getFromResourceDirectory(staticPath)
    }

  }

}

trait UnhandledRouteDebug {
  def myLog(request: HttpRequest): Any => Option[LogEntry] = {
    case x: HttpResponse => {
      println (s"Normal: $request")
      createLogEntry(request,   x.status + " " + x.toString())
    }
    case Rejected(rejections) => {
      println (s"Rejection: $request")
      createLogEntry(request,   " Rejection " + rejections.toString())
    }
    case x => {
      println (s"other: $request")
      createLogEntry(request,   x.toString())
    }
  }

  def createLogEntry(request: HttpRequest, text: String): Some[LogEntry] = {
    Some(LogEntry("#### Request " + request + " => " + text, Logging.DebugLevel))
  }
}
