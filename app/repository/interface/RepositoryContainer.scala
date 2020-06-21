package repository.interface

trait RepositoryContainer {
  def movieRepository: MovieRepository
  def theaterRepository: TheaterRepository
}
