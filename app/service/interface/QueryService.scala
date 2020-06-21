package service.interface

import io.circe.Json

import scala.concurrent.Future

trait QueryService {
  def graphql(json: Json): Future[Either[String, String]]

}
