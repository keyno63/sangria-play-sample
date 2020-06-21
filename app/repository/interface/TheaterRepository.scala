package repository.interface

import domain.interface.Theater

trait TheaterRepository {
  def getTheater(id: Int): Option[Theater]
  def getTheaters(limit: Int, offset: Int): List[Theater]
}
