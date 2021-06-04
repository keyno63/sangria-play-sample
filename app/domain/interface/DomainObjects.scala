package domain.interface

import java.util.Date

case class Movie(
  id: Int,
  title: String,
  start: Date
)

case class Theater(
  id: Int,
  name: String,
  address: String,
  movies: List[Movie]
)
