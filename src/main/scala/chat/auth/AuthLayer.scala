package chat.auth

import chat.dto.UserCreateDto
import chat.dto.UserCreateDto.*
import zio.*
import zio.json.*
import zhttp.http.*


object AuthLayer {
  def apply(): Http[Any, Throwable, Request, Response] =
    Http.collectZIO[Request] {

      case req@(Method.POST -> !! / "auth" / "register") => {
        val userZIO = req.bodyAsString.map(_.fromJson[UserCreateDto])
        for {
          user <- userZIO
          response <- user match {
            case Left(e) => Response.text(e).setStatus(Status.BadRequest)
            case Right(u: UserCreateDto) => ZIO.succeed(Response.json(u.toJsonPretty(UserCreateDto.encoder)))
          }
        } yield response
      }
    }
}
