import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.api.{Configuration, Environment}
import play.shaded.ahc.org.asynchttpclient.{AsyncHttpClient, DefaultAsyncHttpClient}

/**
  * Sets up custom components for Play.
  *
  * https://www.playframework.com/documentation/latest/ScalaDependencyInjection
  */
class Module(environment: Environment, configuration: Configuration)
    extends AbstractModule
    with ScalaModule {

  override def configure(): Unit = {
    bind[StandaloneWSClient].to[StandaloneAhcWSClient]
    bind[AsyncHttpClient].to[DefaultAsyncHttpClient]
  }
}
