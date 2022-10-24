package chat

import chat.config.HttpServerConfig
import chat.greet.GreetingApp
import zhttp.service.Server
import zio.*
import zio.logging.LogFormat
import zio.logging.backend.SLF4J

import java.io.IOException

object Main extends ZIOAppDefault {

  override val bootstrap: ZLayer[Any, Nothing, Unit] = SLF4J.slf4j(LogFormat.default)
  override def run =
    ZIO.service[HttpServerConfig].flatMap { config =>
      ZIO.log(s"Server started at ${config.port} port")
      Server.start(
        port = config.port,
        http = GreetingApp()
      )
    }.provide(
      HttpServerConfig.layer // This layer contains the configuration of the http server
    )

}
