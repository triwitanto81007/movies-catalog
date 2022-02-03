package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (

  var id : Int? = null,
  var name : String? = null

) : Parcelable