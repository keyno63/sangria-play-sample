package repository.interface

import com.google.inject.ImplementedBy
import repository.mock.RepositoryContainerImpl

@ImplementedBy(classOf[RepositoryContainerImpl])
trait RepositoryContainer {
  def movieRepository: MovieRepository
  def theaterRepository: TheaterRepository
}
