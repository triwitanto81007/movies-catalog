package id.namikaze.moviescatalog.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RecipeEntity::class,
        DetailRecipeEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
