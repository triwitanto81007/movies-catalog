package id.namikaze.moviescatalog.data.source.local

import kotlinx.coroutines.flow.Flow

interface IRecipeLocalSource {

    fun getRecipesList(): Flow<List<RecipeEntity>>

    fun getRecipesDetail(key: String): Flow<DetailRecipeEntity>

    suspend fun insertRecipesList(data: List<RecipeEntity>)

    suspend fun insertRecipesDetail(data: DetailRecipeEntity)
}