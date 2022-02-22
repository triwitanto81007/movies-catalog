package id.namikaze.moviescatalog.data.source.remote.network

import id.namikaze.moviescatalog.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String): GenresResponse

    @GET("discover/movie")
    suspend fun getMovieList(@Query("api_key") apiKey: String, @Query("with_genres") withGenres: String, @Query("page") page: String): MoviesResponse

    @GET("search/movie")
    suspend fun getSearchMovieList(@Query("api_key") apiKey: String,@Query("query") query: String): MoviesResponse

    @GET("movie/{id_movie}")
    suspend fun getMovieDetail( @Path("id_movie") idMovie: String, @Query("api_key") apiKey: String): MovieDetailResponse

    @GET("movie/{id_movie}/reviews")
    suspend fun getReview(@Path("id_movie") idMovie: String, @Query("api_key") apiKey: String, @Query("page") page: String): ReviewsResponse

    @GET("movie/{id_movie}/videos")
    suspend fun getTrailer(@Path("id_movie") idMovie: String, @Query("api_key") apiKey: String): TrailersResponse
}