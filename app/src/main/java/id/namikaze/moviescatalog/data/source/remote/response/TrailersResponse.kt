package id.namikaze.moviescatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class TrailersResponse (

  @SerializedName("id"      ) var id      : Int?               = null,
  @SerializedName("results" ) var results : ArrayList<TrailerResponse> = arrayListOf()

)