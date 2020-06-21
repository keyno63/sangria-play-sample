package repository.mock

import domain.interface.Theater
import repository.interface.TheaterRepository

class TheaterRepositoryImpl extends TheaterRepository {
  import MockData._
  override def getTheater(id: Int): Option[Theater] = theaters.find(t => t.id == id)

  override def getTheaters(limit: Int, offset: Int): List[Theater] = theaters.drop(offset).take(limit)

}
