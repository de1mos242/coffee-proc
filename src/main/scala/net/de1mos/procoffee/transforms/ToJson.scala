package net.de1mos.procoffee.transforms

import java.time.{LocalDate, LocalDateTime}

import net.de1mos.procoffee.domain.{UserActivity, BaseEntity, Purchase, User}
import spray.httpx.SprayJsonSupport
import spray.json._

/**
 * Created by De1mos on 14.07.2015.
 */
object ToJson extends DefaultJsonProtocol with SprayJsonSupport {

  implicit object LocalDateJsonFormat extends JsonFormat[LocalDate] {
    override def write(obj: LocalDate): JsValue = JsString(obj.toString)

    override def read(json: JsValue): LocalDate = json match {
      case JsString(jString) => {
        val dateTime = LocalDateTime.parse(jString)
        dateTime.toLocalDate
      }
      case _ => deserializationError("String expected while parse LocalDate")
    }
  }

  implicit object baseEntityFormt extends RootJsonFormat[BaseEntity] {
    override def write(obj: BaseEntity): JsValue = obj match {
      case p:Purchase => p.toJson
    }

    override def read(json: JsValue): BaseEntity = {
      deserializationError("Could not parse BaseEntity")
    }
  }
  implicit val userFormat = jsonFormat3(User)
  implicit val purchaseFormat = jsonFormat4(Purchase)
  implicit val userActivityFormat = jsonFormat4(UserActivity)

}
