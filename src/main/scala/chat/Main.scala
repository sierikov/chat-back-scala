package chat

import chat.config.HttpServerConfig
import chat.greet.GreetingApp
import zhttp.service.Server
import zio.*
import zio.logging.*
import zio.logging.LogFormat.*

import java.io.IOException

object Main extends ZIOAppDefault {


  private val logFormat: LogFormat = timestamp.fixed(19).color(LogColor.BLUE) |-| level.highlight |-| line |-| ifCauseNonEmpty(cause)
  override val bootstrap = Runtime.removeDefaultLoggers >>> console(logFormat)

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
