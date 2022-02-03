package id.namikaze.moviescatalog.domain.repository

import id.namikaze.moviescatalog.data.Resource
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {

    fun getRecipesList(): Flow<Resource<List<Recipe>>>

    fun getRecipesDetail(key: String): Flow<Resource<DetailRecipe>>

}