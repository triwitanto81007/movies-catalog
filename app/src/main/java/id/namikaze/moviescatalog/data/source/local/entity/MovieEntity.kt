package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")
  var id : Int? = null,

  @NonNull
  @ColumnInfo(name = "adult")
  var adult : Boolean? = null,

  @NonNull
  @ColumnInfo(name = "backdrop_path")
  var backdropPath : String? = null,

  @NonNull
  @ColumnInfo(name = "original_language")
  var originalLanguage : String? = null,

  @NonNull
  @ColumnInfo(name = "original_title")
  var originalTitle    : String? = null,

  @NonNull
  @ColumnInfo(name = "overview")
  var overview: String? = null,

  @NonNull
  @ColumnInfo(name = "popularity")
  var popularity: Double? = null,

  @NonNull
  @ColumnInfo(name = "poster_path")
  var posterPath: String? = null,

  @NonNull
  @ColumnInfo(name = "release_date")
  var releaseDate : String? = null,

  @NonNull
  @ColumnInfo(name = "title")
  var title : String? = null,

  @NonNull
  @ColumnInfo(name = "video")
  var video : Boolean? = null,

  @NonNull
  @ColumnInfo(name = "vote_average")
  var voteAverage : Double? = null,

  @NonNull
  @ColumnInfo(name = "vote_count")
  var voteCount : Int? = null

)