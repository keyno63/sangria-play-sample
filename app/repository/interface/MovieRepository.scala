package repository.interface

import com.google.inject.ImplementedBy
import domain.interface.Movie
import repository.mock.MovieRepositoryImpl

@ImplementedBy(classOf[MovieRepositoryImpl])
trait MovieRepository {
  def getMovie(id: Int): Option[Movie]
  def getMovies(limit: Int, offset: Int): List[Movie]
}
