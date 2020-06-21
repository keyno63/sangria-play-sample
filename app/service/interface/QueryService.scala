package service.interface

import io.circe.Json

trait QueryService {
  def graphql(json: Json): Either[String, String]

}
