package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trailer")
data class TrailerEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")
  var id : Int,

  @ColumnInfo(name = "iso_639_1")
  var iso6391 : String?  = null,

  @ColumnInfo(name = "iso_3166_1")
  var iso31661 : String?  = null,

  @ColumnInfo(name = "name")
  var name : String?  = null,

  @ColumnInfo(name = "key")
  var key: String?  = null,

  @ColumnInfo(name = "site")
  var site: String?  = null,

  @ColumnInfo(name = "size")
  var size: Int?     = null,

  @ColumnInfo(name = "type")
  var type: String?  = null,

  @ColumnInfo(name = "official")
  var official: Boolean? = null,

  @ColumnInfo(name = "published_at")
  var publishedAt : String?  = null
)