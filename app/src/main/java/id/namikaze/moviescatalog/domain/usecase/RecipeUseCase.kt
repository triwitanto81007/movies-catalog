package id.namikaze.moviescatalog.domain.usecase

import id.namikaze.moviescatalog.data.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {

    fun getRecipesList(): Flow<Resource<List<Recipe>>>

    fun getRecipesDetail(key: String): Flow<Resource<DetailRecipe>>
}