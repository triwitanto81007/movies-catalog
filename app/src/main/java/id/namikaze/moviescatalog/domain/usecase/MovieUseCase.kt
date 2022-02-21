package id.namikaze.moviescatalog.domain.usecase

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getGenreList(): Flow<Resource<List<Genre>>>

    fun getMovieList(withGenres: Int, page: String): Flow<Resource<List<Movie>>>

    fun getMovieDetail(idMovie: Int): Flow<Resource<MovieDetail>>

    fun getReview(idMovie: Int, page: String): Flow<Resource<List<Review>>>

    fun getTrailer(idMovie: Int): Flow<Resource<Trailer>>
}