package id.namikaze.moviescatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class TrailersResponse (

  @SerializedName("id")
  val id : Int,

  @SerializedName("results")
  val results : List<TrailerResponse>

)