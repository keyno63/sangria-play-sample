package repository.interface

import domain.interface.Movie

trait MovieRepository {
  def getMovie(id: Int): Option[Movie]
  def getMovies(limit: Int, offset: Int): List[Movie]
}
