package id.namikaze.moviescatalog.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.namikaze.moviescatalog.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM genres")
    fun getGenreList(): Flow<List<GenreEntity>>

    @Query("SELECT * FROM movies  WHERE genre_ids LIKE '%' || :genreId || '%'")
    fun getMovieList(genreId: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM review WHERE id = :id")
    fun getReview(id: Int): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM trailer WHERE id = :id")
    fun getTrailer(id: Int): Flow<TrailerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreList(data: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(data: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(data: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReview(data: List<ReviewEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrailer(data: TrailerEntity)
}