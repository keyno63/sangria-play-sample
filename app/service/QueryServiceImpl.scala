package service

import domain.graphql.SchemaDefinition
import io.circe.{Json, parser}
import io.circe.optics.JsonPath.root
import repository.mock.RepositoryContainerImpl
import sangria.ast.Document
import sangria.execution.deferred.DeferredResolver
import sangria.execution.{ErrorWithResolver, Executor, QueryAnalysisError}
import sangria.parser.QueryParser
import sangria.marshalling.circe._
import service.interface.QueryService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class QueryServiceImpl()(implicit ec: ExecutionContext) extends QueryService {

  override def graphql(json: Json): Future[Either[String, String]] = {
    val query = root.query.string.getOption(json)
    val variablesStr: Option[String] = root.variables.string.getOption(json)

    query.map(QueryParser.parse(_)) match {
      case Some(Success(ast)) =>
        variablesStr.map(parser.parse) match {
          case Some(Left(error)) => Future(Left(s"$error"))
          case Some(Right(_)) => executeGraphQL(ast)
          case None => executeGraphQL(ast)
        }
      case Some(Failure(error)) =>
        Future(Left(s"$error"))
      case None =>
        Future(Left(s"No query to execute"))
    }
  }

  private def executeGraphQL(query: Document): Future[Either[String, String]] = {
    Executor.execute(
      SchemaDefinition.CustomSchema, query, RepositoryContainerImpl,
      deferredResolver = DeferredResolver.fetchers(SchemaDefinition.movies)
    ).map(x => Right(s"${x}"))
    .recover {
      case error: QueryAnalysisError => Left(error.resolveError.toString())
      case error: ErrorWithResolver => Left(error.resolveError.toString())
    }
  }
}
