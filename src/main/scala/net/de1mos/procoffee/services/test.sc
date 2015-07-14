val js = """{"date":"2015-07-15T17:00:00.000Z","cost":123.01,"user":{"id":2,"firstName":"John","lastName":"Doe"}}"""
import net.de1mos.procoffee.domain.Purchase
import net.de1mos.procoffee.transforms.ToJson._
import spray.json._

val jss = js.asJson
val p = jss.convertTo[Purchase]
p.cost