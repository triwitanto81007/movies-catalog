package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class ReviewEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "author_id")
  val idAuthor : String,

  @NonNull
  @ColumnInfo(name = "id")
  val id : Int?,

  @ColumnInfo(name = "author")
  val author : String?,

  @ColumnInfo(name = "username")
  val username : String?,

  @ColumnInfo(name = "avatar_path")
  val avatarPath : String?,

  @ColumnInfo(name = "rating")
  val rating : Int?,

  @ColumnInfo(name = "content")
  val content : String?,

  @ColumnInfo(name = "created_at")
  val createdAt : String?,

  @ColumnInfo(name = "updated_at")
  val updatedAt : String?,

  @ColumnInfo(name = "url")
  val url : String?

)