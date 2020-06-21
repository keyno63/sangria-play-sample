package repository.interface

import com.google.inject.ImplementedBy
import domain.interface.Theater
import repository.mock.TheaterRepositoryImpl

@ImplementedBy(classOf[TheaterRepositoryImpl])
trait TheaterRepository {
  def getTheater(id: Int): Option[Theater]
  def getTheaters(limit: Int, offset: Int): List[Theater]
}
