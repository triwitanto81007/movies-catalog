package id.namikaze.moviescatalog.data.source.remote.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("recipes")
    suspend fun getRecipesList(): BaseResponse<List<RecipeResponse>>

    @GET("recipe/{key}")
    suspend fun getRecipesDetail(@Path("key") key: String): BaseResponse<DetailRecipeResponse>
}