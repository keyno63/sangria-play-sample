package repository.mock

import java.util.Date

import domain.interface.{Movie, Theater}

object MockData {
  val movies = List(
    Movie(1, "ドクター／ドリトル", new Date("2020/06/19")),
    Movie(2, "プロメア", new Date("2020/06/01")),
    Movie(3, "はちどり", new Date("2020/06/20")),
  )

  val theaters = List(
    Theater(1, "バルト9", "東京都新宿区新宿３丁目１−２６ 新宿三丁目ビル 9階",
      List(
        Movie(1, "ドクター／ドリトル", new Date("2020/06/19")),
        Movie(2, "プロメア", new Date("2020/06/01"))
      )
    )
  )

}
