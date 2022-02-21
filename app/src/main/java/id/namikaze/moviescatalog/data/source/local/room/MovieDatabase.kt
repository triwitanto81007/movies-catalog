package id.namikaze.moviescatalog.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.namikaze.moviescatalog.data.source.local.entity.*

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class,
        MovieDetailEntity::class,
        ReviewEntity::class,
        TrailerEntity::class

    ],
    version = 2,
    exportSchema = false
)

abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
