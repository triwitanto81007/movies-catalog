package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review (

  val id : Int?,
  val idAuthor : String?,
  val author : String?,
  val username : String?,
  val avatarPath : String?,
  val rating : Int?,
  val content : String?,
  val createdAt : String?,
  val updatedAt : String?,
  val url : String?

) : Parcelable