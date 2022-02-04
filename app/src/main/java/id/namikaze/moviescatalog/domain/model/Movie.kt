package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (

  var id : Int? = null,
  var adult : Boolean? = null,
  var backdropPath : String? = null,
  var genreIds : String? = null,
  var originalLanguage : String? = null,
  var originalTitle    : String? = null,
  var overview: String? = null,
  var popularity: Double? = null,
  var posterPath: String? = null,
  var releaseDate : String? = null,
  var title : String? = null,
  var video : Boolean? = null,
  var voteAverage : Double? = null,
  var voteCount : Int? = null

) : Parcelable