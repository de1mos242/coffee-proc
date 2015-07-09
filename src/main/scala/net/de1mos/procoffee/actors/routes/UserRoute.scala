package net.de1mos.procoffee.actors.routes

import akka.actor.{Actor, Props}
import akka.event.slf4j.SLF4JLogging
import net.de1mos.procoffee.domain.User
import net.de1mos.procoffee.domain.UserJsonProtocol._
import net.de1mos.procoffee.services.UserService
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * Factory method for Props configuration files for actors
 */
object UserRoute {
  def props: Props = Props(new UserRoute())
}

/**
 * Actor that handles requests that begin with "person"
 */
class UserRoute() extends Actor with UserRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(userRoute)
}

/**
 * Separate routing logic in an HttpService trait so that the
 * routing logic can be tested outside of an actor system in specs/mockito tests
 */
trait UserRouteTrait extends HttpService with SprayJsonSupport with SLF4JLogging {

  private val userService: UserService = UserService()

  val userRoute = {
    get {
      pathEnd {
        complete {
          log.debug("Hitting Get All Users")
          val users = userService.getUsers
          users match {
            case head :: tail => users
            case Nil => StatusCodes.NoContent
          }
        }
      } ~
        path(LongNumber) { userId =>
          log.debug(s"Hitting Get User by Id:${userId}")
          val user = userService.getUserById(userId)
          complete(user)
        }
    } ~
      (post & pathEnd) {
        entity(as[User]) { user =>
          log.debug("posting to create a user")
          val newPerson = userService.addUser(user)
          complete(StatusCodes.Created, newPerson)
        }
      } ~
      (put & path(LongNumber) & pathEnd) { userId =>
        entity(as[User]) { user =>
          log.debug(s"updating a user with the id: ${userId}")
          val updatedPerson = userService.updateUser(user.copy(id = Some(userId)))
          updatedPerson match {
            case true => complete(StatusCodes.NoContent)
            case false => complete(StatusCodes.NotFound)
          }
        }
      } ~
      (delete & path(LongNumber) & pathEnd) { userId =>
        log.debug(s"deleting a user with the id: ${userId}")
        userService.deleteUser(userId)
        complete(StatusCodes.NoContent)
      }
  }
}
