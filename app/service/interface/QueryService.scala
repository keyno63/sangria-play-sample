package service.interface

import com.google.inject.ImplementedBy
import io.circe.Json
import service.QueryServiceImpl

import scala.concurrent.Future

@ImplementedBy(classOf[QueryServiceImpl])
trait QueryService {
  def graphql(json: Json): Future[Either[String, String]]

}
