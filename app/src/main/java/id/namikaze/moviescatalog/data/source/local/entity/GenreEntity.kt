package id.namikaze.moviescatalog.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity (

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")
  val id : Int,

  @ColumnInfo(name = "name")
  val name : String?

)