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
  val id : Int,

  @ColumnInfo(name = "name")
  val name : String?,

  @ColumnInfo(name = "key")
  val key: String?,

  @ColumnInfo(name = "site")
  val site: String?,

  @ColumnInfo(name = "size")
  val size: Int?,

  @ColumnInfo(name = "type")
  val type: String?,

  @ColumnInfo(name = "official")
  val official: Boolean?,

  @ColumnInfo(name = "published_at")
  val publishedAt : String?
)