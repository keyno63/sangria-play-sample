package domain.interface

trait Theater {
  val id: Int
  val name: String
  val address: String
  val movies: List[Movie]
}
