package id.namikaze.moviescatalog.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailer (

  var id : Int? = null,
  var iso6391 : String?  = null,
  var iso31661 : String?  = null,
  var name : String?  = null,
  var key: String?  = null,
  var site: String?  = null,
  var size: Int?     = null,
  var type: String?  = null,
  var official: Boolean? = null,
  var publishedAt : String?  = null

) : Parcelable