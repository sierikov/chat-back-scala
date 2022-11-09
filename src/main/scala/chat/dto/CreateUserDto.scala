package chat.dto

import zio.json.{DeriveJsonCodec, DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

case class CreateUserDto(email: String, firstName: String, lastName: String, password: String)
object CreateUserDto {
  implicit val decoder: JsonDecoder[CreateUserDto] = DeriveJsonDecoder.gen[CreateUserDto]
  implicit val encoder: JsonEncoder[CreateUserDto] = DeriveJsonEncoder.gen[CreateUserDto]
}
