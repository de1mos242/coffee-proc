package net.de1mos.procoffee.actors.routes

import akka.actor.{Actor, Props}
import net.de1mos.procoffee.domain.UserActivity
import net.de1mos.procoffee.services.UserActivityService
import net.de1mos.procoffee.transforms.ToJson
import spray.httpx.unmarshalling.FromRequestUnmarshaller
import spray.json.JsValue
import net.de1mos.procoffee.transforms.ToJson._
import spray.routing.Route

import scala.util.Try

/**
 * Created by De1mos on 15.07.2015.
 */
class UserActivityRoute extends Actor with UserActivityRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(userActivityRoute)
}

object UserActivityRoute {
  def props : Props = Props(new UserActivityRoute())
}

trait UserActivityRouteTrait extends CrudRoute[UserActivity, UserActivityService] {

  override def crudService: UserActivityService = UserActivityService()

  override def getRequestUnmarshaller(): FromRequestUnmarshaller[UserActivity] = as[UserActivity]

  override def convertToJson(entity: UserActivity): JsValue = ToJson.userActivityFormat.write(entity)

  val userActivityCustomRoute : Route = {
    get {
      pathEnd {
        parameter("userId" ? "") { userId =>
          complete {
            log.debug(s"Hitting Get All ${crudService.getClass} with userId ${userId}")
            val entities = Try(userId.toLong).toOption match {
              case None => crudService.getAll
              case Some(userParsedId) => crudService.getByUserId(userParsedId)
            }
            convertToJson(entities)
          }
        }
      }

    }

  }

  val userActivityRoute = userActivityCustomRoute ~ crudRoutes
}
