package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRemoteDataSource(private val apiService: ApiService) : IRecipeRemoteSource {
    override fun getRecipesList(): Flow<ApiResponse<List<RecipeResponse>>> {
        return flow {
            try {
                val response = apiService.getRecipesList()
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getRecipesDetail(key: String): Flow<ApiResponse<DetailRecipeResponse>> {
        return flow {
            try {
                val response = apiService.getRecipesDetail(key)
                if (response.results.title?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}