package id.namikaze.moviescatalog.domain.usecase

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeInteractor(private val recipeRepository: IRecipeRepository) : RecipeUseCase {
    override fun getRecipesList(): Flow<Resource<List<Recipe>>> =
        recipeRepository.getRecipesList()

    override fun getRecipesDetail(key: String): Flow<Resource<DetailRecipe>> =
        recipeRepository.getRecipesDetail(key)
}