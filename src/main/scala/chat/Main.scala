package chat

import chat.config.HttpServerConfig
import chat.greet.GreetingApp
import zhttp.service.Server
import zio.*
import zio.logging.LogFormat
import zio.logging.backend.SLF4J

import java.io.IOException

object Main extends ZIOAppDefault {

  override val bootstrap: ZLayer[Any, Nothing, Unit] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j(LogFormat.colored)

  override def run = for {
    _ <- ZIO.logInfo("Starting the server")
    config <- ZIO.service[HttpServerConfig].provide(HttpServerConfig.layer)
    _ <- ZIO.logInfo(s"Server is running on ${config.port}")
    server <- Server.start(
        port = config.port,
        http = GreetingApp()
      )
  } yield server
}
