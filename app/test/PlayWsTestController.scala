package test

import akka.http.scaladsl.model.{ContentType, ContentTypes}
import javax.inject.Inject
import play.api.libs.ws.StandaloneWSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PlayWsTestController @Inject() (
    wsClient: StandaloneWSClient
)(
    val controllerComponents: ControllerComponents
) extends BaseController {

  def get(): Action[AnyContent] = Action.async { implicit req =>
    val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg"
    wsClient
      .url(imageUrl)
      .withMethod("GET")
      .stream()
      .flatMap { response =>
        val contentType = response
          .header("Content-Type")
          .map(ContentType.parse)
          .flatMap(_.toOption)
          .getOrElse(ContentTypes.`application/octet-stream`)
        Future(())
      }
      .map(_ => Ok("you made it!"))
  }
}
