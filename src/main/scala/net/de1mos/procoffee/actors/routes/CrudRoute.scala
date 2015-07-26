package net.de1mos.procoffee.actors.routes

import akka.event.slf4j.SLF4JLogging
import net.de1mos.procoffee.domain.BaseEntity
import net.de1mos.procoffee.services.CrudService
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.httpx.marshalling.ToResponseMarshallable
import spray.httpx.unmarshalling._
import spray.json.{JsArray, JsValue}
import spray.routing.HttpService
import net.de1mos.procoffee.transforms.ToJson._

/**
 * Created by De1mos on 13.07.2015.
 */
trait CrudRoute[Entity <: BaseEntity, Service <: CrudService[Entity]] extends HttpService with SprayJsonSupport with SLF4JLogging {
  def crudService : Service

  def convertToJson(entities : List[Entity]) : ToResponseMarshallable = entities match {
    case head :: tail => {
      RootJsArrayFormat.write(JsArray(entities.map(entity => convertToJson(entity))))
    }
    case Nil => StatusCodes.NoContent
  }
  def convertToJson(entity : Entity) : JsValue
  def getRequestUnmarshaller() : FromRequestUnmarshaller[Entity]

  val crudRoutes = {
    get {
      pathEnd {
        complete {
          log.debug("Hitting Get All %s".format(crudService.getClass))
          val entities = crudService.getAll
          convertToJson(entities)
        }
      } ~
        path(LongNumber) { entityId =>
          log.debug(s"Hitting Get ${crudService.getClass} by Id:${entityId}")
          val entity = crudService.findById(entityId)
          entity match {
            case Some(e) => complete(convertToJson(e).asJsObject)
            case None => complete(StatusCodes.NoContent)
          }
        }
    } ~
      (post & pathEnd) {
        entity(getRequestUnmarshaller) { crudEntity =>
          log.debug(s"posting to create a ${crudEntity getClass}")
          val newEntity = crudService.add(crudEntity)
          complete(convertToJson(newEntity).asJsObject)
        }
      } ~
      (put & path(LongNumber) & pathEnd) { entityId =>
        entity(getRequestUnmarshaller) { crudEntity =>
          log.debug(s"updating a ${crudEntity.getClass} with the id: ${entityId}")
          crudService.findById(entityId) match {
            case Some(_) =>  crudService.update(entityId, crudEntity); complete(StatusCodes.NoContent)
            case None => complete(StatusCodes.NoContent)
          }
        }
      } ~
      (delete & path(LongNumber) & pathEnd) { entityId =>
        log.debug(s"deleting with the id: ${entityId}")
        crudService.delete(entityId)
        complete(StatusCodes.NoContent)
      }
  }
}
