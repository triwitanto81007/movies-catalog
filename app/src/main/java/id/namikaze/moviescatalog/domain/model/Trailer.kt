package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer (

  val id : Int?,
  val name : String?,
  val key: String?,
  val site: String?,
  val size: Int?,
  val type: String?,
  val official: Boolean?,
  val publishedAt : String?

) : Parcelable