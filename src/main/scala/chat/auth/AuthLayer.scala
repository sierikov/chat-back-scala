package chat.auth

import chat.dto.CreateUserDto
import chat.dto.CreateUserDto.*
import zhttp.http.*
import zio.json.{DecoderOps, EncoderOps}

object AuthLayer {
  def apply(): Http[Any, Nothing, Request, Response] =
    Http.collect[Request] {

      case req@(Method.POST -> !! / "auth" / "register") =>

        req.bodyAsString
          .flatMap(
            _.fromJson[CreateUserDto].flatMap {
              u => Response.text(s"Hello ${u.lastName}!\nFull Object: ${u.toJson}")
            }.orElse(Response.text("Failed"))
          )
          .orDie



    }
}
