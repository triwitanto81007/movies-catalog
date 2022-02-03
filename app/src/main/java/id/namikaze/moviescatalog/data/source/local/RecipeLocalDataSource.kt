package id.namikaze.moviescatalog.data.source.local

import id.namikaze.moviescatalog.data.source.local.room.RecipeDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeLocalDataSource(private val recipeDao: RecipeDao): IRecipeLocalSource {
    override fun getRecipesList(): Flow<List<RecipeEntity>> = recipeDao.getRecipesList()

    override fun getRecipesDetail(key: String): Flow<DetailRecipeEntity> = recipeDao.getRecipesDetail(key)

    override suspend fun insertRecipesList(data: List<RecipeEntity>) {
        GlobalScope.launch {
            recipeDao.insertRecipesList(data)
        }
    }

    override suspend fun insertRecipesDetail(data: DetailRecipeEntity) {
        recipeDao.insertRecipesDetail(data)
    }
}