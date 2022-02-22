package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail (

  val id : Int?,
  val adult : Boolean?,
  val backdropPath : String?,
  val originalLanguage : String?,
  val originalTitle : String?,
  val overview : String?,
  val posterPath : String?,
  val releaseDate : String?,
  val title: String?,
  val video: Boolean?,
  val voteAverage : Double?,
  val voteCount : Int?

) : Parcelable