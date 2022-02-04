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
  var idAuthor : String,

  @NonNull
  @ColumnInfo(name = "id")
  var id : Int? = null,

  @ColumnInfo(name = "author")
  var author : String? = null,

  @ColumnInfo(name = "username")
  var username : String? = null,

  @ColumnInfo(name = "avatar_path")
  var avatarPath : String? = null,

  @ColumnInfo(name = "rating")
  var rating : Int? = null,

  @ColumnInfo(name = "content")
  var content : String? = null,

  @ColumnInfo(name = "created_at")
  var createdAt : String? = null,

  @ColumnInfo(name = "updated_at")
  var updatedAt : String? = null,

  @ColumnInfo(name = "url")
  var url : String? = null

)