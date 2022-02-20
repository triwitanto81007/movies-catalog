package id.namikaze.moviescatalog.data.source.local

import id.namikaze.moviescatalog.data.source.local.entity.*
import id.namikaze.moviescatalog.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val movieDao: MovieDao): IMovieLocalSource {
    override fun getGenreList(): Flow<List<GenreEntity>> = movieDao.getGenreList()

    override fun getMovieList(genreId: String, limit: Int, offset: Int): Flow<List<MovieEntity>> = movieDao.getMovieList(genreId, limit, offset)

    override fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = movieDao.getMovieDetail(id)

    override fun getReview(id: Int, limit: Int, offset: Int): Flow<List<ReviewEntity>> = movieDao.getReview(id, limit, offset)

    override fun getTrailer(id: Int): Flow<TrailerEntity> = movieDao.getTrailer(id)

    override suspend fun insertGenreList(data: List<GenreEntity>) {
        movieDao.insertGenreList(data)
    }

    override suspend fun insertMovieList(data: List<MovieEntity>) {
        movieDao.insertMovieList(data)
    }

    override suspend fun insertMovieDetail(data: MovieDetailEntity) {
        movieDao.insertMovieDetail(data)
    }

    override suspend fun insertReview(data: List<ReviewEntity>) {
        movieDao.insertReview(data)
    }

    override suspend fun insertTrailer(data: TrailerEntity) {
        movieDao.insertTrailer(data)
    }
}