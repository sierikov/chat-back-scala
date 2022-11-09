package chat.middleware

import zhttp.http.{Http, Middleware, Request, Response}
import zio.ZIO

object LoggerMiddleware {
  def log[R, E >: Exception]: Middleware[R, E, Request, Response, Request, Response] =
    new Middleware[R, E, Request, Response, Request, Response] {
      override def apply[R1 <: R, E1 >: E](http: Http[R1, E1, Request, Response]): Http[R1, E1, Request, Response] = {
        http
          .contramapZIO[R1, E1, Request](request => for {
            _ <- ZIO.logInfo(s"| Request ${request.method} on ${request.url}")
          } yield request)
          .mapZIO[R1, E1, Response](response => for {
            _ <- ZIO.log(s"| Response status:  ${response.status}")
          } yield response)
      }
    }
}
