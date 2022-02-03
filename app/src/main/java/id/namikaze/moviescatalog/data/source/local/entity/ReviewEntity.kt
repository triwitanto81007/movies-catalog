package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class ReviewEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")
  var id : String? = null,

  @NonNull
  @ColumnInfo(name = "author")
  var author : String? = null,

  @NonNull
  @ColumnInfo(name = "username")
  var username : String? = null,

  @NonNull
  @ColumnInfo(name = "avatar_path")
  var avatarPath : String? = null,

  @NonNull
  @ColumnInfo(name = "rating")
  var rating : Int? = null,

  @NonNull
  @ColumnInfo(name = "content")
  var content : String? = null,

  @NonNull
  @ColumnInfo(name = "created_at")
  var createdAt : String? = null,

  @NonNull
  @ColumnInfo(name = "updated_at")
  var updatedAt : String? = null,

  @NonNull
  @ColumnInfo(name = "url")
  var url : String? = null

)