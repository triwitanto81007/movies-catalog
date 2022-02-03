package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRecipeRemoteSource {
    fun getRecipesList(): Flow<ApiResponse<List<RecipeResponse>>>

    fun getRecipesDetail(key: String): Flow<ApiResponse<DetailRecipeResponse>>
}