package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review (

  var id : Int? = null,
  var idAuthor : String? = null,
  var author : String? = null,
  var username : String? = null,
  var avatarPath : String? = null,
  var rating : Int? = null,
  var content : String? = null,
  var createdAt : String? = null,
  var updatedAt : String? = null,
  var url : String? = null

) : Parcelable