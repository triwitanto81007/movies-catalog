package id.namikaze.moviescatalog.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getRecipesList(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE title = :key")
    fun getRecipesDetail(key: String): Flow<DetailRecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipesList(data: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipesDetail(data: DetailRecipeEntity)
}