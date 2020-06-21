package repository.mock

import repository.interface.{MovieRepository, RepositoryContainer, TheaterRepository}

object RepositoryContainerImpl extends RepositoryContainer {
  override def movieRepository: MovieRepository = new MovieRepositoryImpl

  override def theaterRepository: TheaterRepository = new TheaterRepositoryImpl

}
