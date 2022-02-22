package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow

interface IMovieRemoteSource {
    fun getGenreList(): Flow<ApiResponse<List<GenreResponse>>>

    fun getMovieList(withGenres: String, page: String): Flow<ApiResponse<List<MovieResponse>>>

    fun getSearchMovieList(query: String): Flow<ApiResponse<List<MovieResponse>>>

    fun getMovieDetail(idMovie: String): Flow<ApiResponse<MovieDetailResponse>>

    fun getReview(idMovie: String, page: String): Flow<ApiResponse<ReviewsResponse>>

    fun getTrailer(idMovie: String): Flow<ApiResponse<TrailersResponse>>

}