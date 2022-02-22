package id.namikaze.moviescatalog.domain.repository

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getGenreList(): Flow<Resource<List<Genre>>>

    fun getMovieList(withGenres: Int, page: String): Flow<Resource<List<Movie>>>

    fun getSearchMovieList(query: String, page: String): Flow<Resource<List<Movie>>>

    fun getMovieDetail(idMovie: Int): Flow<Resource<MovieDetail>>

    fun getReview(idMovie: Int, page: String): Flow<Resource<List<Review>>>

    fun getTrailer(idMovie: Int): Flow<Resource<Trailer>>


}