package id.namikaze.moviescatalog.data.source.local

import id.namikaze.moviescatalog.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

interface IMovieLocalSource {

    fun getGenreList(): Flow<List<GenreEntity>>

    fun getMovieList(genreId: String, limit: Int, offset: Int ): Flow<List<MovieEntity>>

    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    fun getReview(id: Int, limit: Int, offset: Int): Flow<List<ReviewEntity>>

    fun getTrailer(id: Int): Flow<TrailerEntity>

    suspend fun insertGenreList(data: List<GenreEntity>)

    suspend fun insertMovieList(data: List<MovieEntity>)

    suspend fun insertMovieDetail(data: MovieDetailEntity)

    suspend fun insertReview(data: List<ReviewEntity>)

    suspend fun insertTrailer(data: TrailerEntity)
}