package id.namikaze.moviescatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class AuthorDetails (

  @SerializedName("name")
  val name : String?,

  @SerializedName("username")
  val username : String?,

  @SerializedName("avatar_path")
  val avatarPath : String?,

  @SerializedName("rating")
  val rating : Int?

)