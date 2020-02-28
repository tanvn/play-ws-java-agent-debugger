package test

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentType, ContentTypes}
import akka.stream.{Materializer, SystemMaterializer}
import javax.inject.Inject
//import play.api.libs.json.Json
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PlayWsTestController @Inject() (
    val controllerComponents: ControllerComponents
) extends BaseController {

  def get(): Action[AnyContent] = Action { implicit req =>
    import play.shaded.ahc.org.asynchttpclient._

    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: Materializer = SystemMaterializer(system).materializer

    val asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
      .setMaxRequestRetry(0)
      .setShutdownQuietPeriod(0)
      .setShutdownTimeout(0)
      .build
    val asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfig)
    val wsClient = new StandaloneAhcWSClient(asyncHttpClient)

    val imageUrl =
      "https://vastphotos.com/files/uploads/photos/10245/milky-way-astrophotography-l.jpg"
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
    Ok("you made it!")
  }
}
