package id.namikaze.moviescatalog.domain.repository

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.response.*
import id.namikaze.moviescatalog.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getGenreList(apiKey: String): Flow<Resource<List<Genre>>>

    fun getMovieList(apiKey: String, withGenres: Int): Flow<Resource<List<Movie>>>

    fun getMovieDetail(apiKey: String, idMovie: Int): Flow<Resource<MovieDetail>>

    fun getReview(apiKey: String, idMovie: Int): Flow<Resource<List<Review>>>

    fun getTrailer(apiKey: String, idMovie: Int): Flow<Resource<Trailer>>


}