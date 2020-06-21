package domain.graphql

import domain.interface.{Movie, Theater}
import repository.interface.RepositoryContainer
import sangria.execution.deferred.{Fetcher, HasId}
import sangria.schema.{Argument, Field, IntType, ListType, ObjectType, OptionInputType, OptionType, Schema, StringType, fields, interfaces}

import scala.concurrent.Future

object SchemaDefinition {
  val ID: Argument[Int] = Argument("id", IntType, description = "id of the object")

  val LimitArg: Argument[Int] = Argument("limit", OptionInputType(IntType), defaultValue = 20)
  val OffsetArg: Argument[Int] = Argument("offset", OptionInputType(IntType), defaultValue = 0)

  val movies: Fetcher[RepositoryContainer, Movie, Movie, Int] = Fetcher.caching(
    (ctx: RepositoryContainer, ids: Seq[Int]) =>
      Future.successful(ids.flatMap(id => ctx.movieRepository.getMovie(id))))(HasId(_.id))

  val theaters: Fetcher[RepositoryContainer, Theater, Theater, Int] = Fetcher.caching(
    (ctx: RepositoryContainer, ids: Seq[Int]) =>
      Future.successful(ids.flatMap(id => ctx.theaterRepository.getTheater(id))))(HasId(_.id))

  /* 実際のオブジェクト */
  val Movie: ObjectType[RepositoryContainer, Movie] =
    ObjectType(
      "Movie",
      "Sample data, Movie.",
      interfaces[RepositoryContainer, Movie](),
      fields[RepositoryContainer, Movie](
        Field(
          "id",
          IntType,
          Some("id"),
          resolve = _.value.id
        ),
        Field(
          "title",
          StringType,
          Some("title"),
          resolve = _.value.title
        ),
        Field(
          "start",
          StringType,
          Some("start"),
          resolve = _.value.start.toString
        )
      )
    )

  val Theater: ObjectType[RepositoryContainer, Theater] =
    ObjectType(
      "Theater",
      "Sample data, Theater.",
      interfaces[RepositoryContainer, Theater](),
      fields[RepositoryContainer, Theater](
        Field(
          "id",
          IntType,
          Some("id"),
          resolve = _.value.id
        ),
        Field(
          "name",
          StringType,
          Some("name"),
          resolve = _.value.name
        ),
        Field(
          "address",
          StringType,
          Some("address"),
          resolve = _.value.address
        ),
        Field(
          "movies",
          ListType(Movie),
          Some("movies"),
          resolve = _.value.movies
        )
      )
    )

  val Query: ObjectType[RepositoryContainer, Unit] = ObjectType(
    "Query",
    fields[RepositoryContainer, Unit](
      Field(
        "movie",
        OptionType(Movie),
        arguments = ID :: Nil,
        resolve = (ctx) => ctx.ctx.movieRepository.getMovie(ctx.arg(ID))
      ),
      Field(
        "movies",
        ListType(Movie),
        arguments = LimitArg :: OffsetArg :: Nil,
        resolve = ctx => ctx.ctx.movieRepository.getMovies(ctx arg LimitArg, ctx arg OffsetArg)
      ),
      Field(
        "theater",
        OptionType(Theater),
        arguments = ID :: Nil,
        resolve = (ctx) => ctx.ctx.theaterRepository.getTheater(ctx.arg(ID))
      ),
      Field(
        "theaters",
        ListType(Theater),
        arguments = LimitArg :: OffsetArg :: Nil,
        resolve = ctx => ctx.ctx.theaterRepository.getTheaters(ctx arg LimitArg, ctx arg OffsetArg)
      ),
    )
  )
  // schema 定義の変数
  val CustomSchema: Schema[RepositoryContainer, Unit] = Schema(Query)

}
