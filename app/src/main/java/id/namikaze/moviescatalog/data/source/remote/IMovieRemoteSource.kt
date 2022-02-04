package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow

interface IMovieRemoteSource {
    fun getGenreList(apiKey: String): Flow<ApiResponse<List<GenreResponse>>>

    fun getMovieList(apiKey: String, withGenres: String): Flow<ApiResponse<List<MovieResponse>>>

    fun getMovieDetail(apiKey: String, idMovie: String): Flow<ApiResponse<MovieDetailResponse>>

    fun getReview(apiKey: String, idMovie: String): Flow<ApiResponse<ReviewsResponse>>

    fun getTrailer(apiKey: String, idMovie: String): Flow<ApiResponse<TrailersResponse>>

}