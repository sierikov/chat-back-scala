package chat.dto

import zio.json.{DeriveJsonCodec, DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

case class UserCreateDto(email: String, firstName: String, lastName: String, password: String)
object UserCreateDto {
  implicit val decoder: JsonDecoder[UserCreateDto] = DeriveJsonDecoder.gen[UserCreateDto]
  implicit val encoder: JsonEncoder[UserCreateDto] = DeriveJsonEncoder.gen[UserCreateDto]
}
