package repository.mock

import domain.interface.Movie
import repository.interface.MovieRepository

class MovieRepositoryImpl extends MovieRepository {
  import MockData._
  override def getMovie(id: Int): Option[Movie] = movies.find(m => m.id == id)

  override def getMovies(limit: Int, offset: Int): List[Movie] = movies.drop(offset).take(limit)

}
