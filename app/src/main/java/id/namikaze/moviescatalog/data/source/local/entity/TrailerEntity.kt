package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trailer")
data class TrailerEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")var id : Int? = null,

  @NonNull
  @ColumnInfo(name = "iso_639_1")
  var iso6391 : String?  = null,

  @NonNull
  @ColumnInfo(name = "iso_3166_1")
  var iso31661 : String?  = null,

  @NonNull
  @ColumnInfo(name = "name")
  var name : String?  = null,

  @NonNull
  @ColumnInfo(name = "key")
  var key: String?  = null,

  @NonNull
  @ColumnInfo(name = "site")
  var site: String?  = null,

  @NonNull
  @ColumnInfo(name = "size")
  var size: Int?     = null,

  @NonNull
  @ColumnInfo(name = "id")
  var type: String?  = null,

  @NonNull
  @ColumnInfo(name = "official")
  var official: Boolean? = null,

  @NonNull
  @ColumnInfo(name = "published_at")
  var publishedAt : String?  = null
)