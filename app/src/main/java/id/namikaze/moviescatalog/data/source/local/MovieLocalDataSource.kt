package id.namikaze.moviescatalog.data.source.local

import android.util.Log
import id.namikaze.moviescatalog.data.source.local.entity.*
import id.namikaze.moviescatalog.data.source.local.room.MovieDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieLocalDataSource(private val movieDao: MovieDao): IMovieLocalSource {
    override fun getGenreList(): Flow<List<GenreEntity>> = movieDao.getGenreList()

    override fun getMovieList(genreId: String): Flow<List<MovieEntity>> = movieDao.getMovieList(genreId)

    override fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = movieDao.getMovieDetail(id)

    override fun getReview(id: Int): Flow<List<ReviewEntity>> = movieDao.getReview(id)

    override fun getTrailer(id: Int): Flow<TrailerEntity> = movieDao.getTrailer(id)

    override suspend fun insertGenreList(data: List<GenreEntity>) {
        GlobalScope.launch {
            movieDao.insertGenreList(data)
        }
    }

    override suspend fun insertMovieList(data: List<MovieEntity>) {
        GlobalScope.launch {
            Log.d("JumlahData", data.size.toString())
            movieDao.insertMovieList(data)
        }
    }

    override suspend fun insertMovieDetail(data: MovieDetailEntity) {
        GlobalScope.launch {
            movieDao.insertMovieDetail(data)
        }
    }

    override suspend fun insertReview(data: List<ReviewEntity>) {
        GlobalScope.launch {
            movieDao.insertReview(data)
        }
    }

    override suspend fun insertTrailer(data: TrailerEntity) {
        GlobalScope.launch {
            movieDao.insertTrailer(data)
        }
    }
}