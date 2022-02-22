package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.R
import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.network.ApiService
import id.namikaze.moviescatalog.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) : IMovieRemoteSource {
    override fun getGenreList(): Flow<ApiResponse<List<GenreResponse>>> {
        //flow -> adalah builder untuk membuat Flow
        return flow {
            try {
                val response = apiService.getGenreList(BuildConfig.API_KEY)
                // emit -> operator untuk mengirimkan value ke dalam Flow
                emit(ApiResponse.Success(response.genres))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO) //mendefinisikan proses mengirim data dilakukan di mana
    }

    override fun getMovieList(withGenres: String, page: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList(BuildConfig.API_KEY, withGenres, page)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getSearchMovieList(query: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getSearchMovieList(BuildConfig.API_KEY, query)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getMovieDetail(idMovie: String): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(idMovie, BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getReview(idMovie: String, page: String): Flow<ApiResponse<ReviewsResponse>> {
        return flow {
            try {
                val response = apiService.getReview(idMovie, BuildConfig.API_KEY, page)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getTrailer(idMovie: String): Flow<ApiResponse<TrailersResponse>> {
        return flow {
            try {
                val response = apiService.getTrailer(idMovie, BuildConfig.API_KEY)
                if (response.results.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error("No trailer available"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}