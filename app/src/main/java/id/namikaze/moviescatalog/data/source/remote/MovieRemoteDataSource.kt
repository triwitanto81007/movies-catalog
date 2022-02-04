package id.namikaze.moviescatalog.data.source.remote

import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.network.ApiService
import id.namikaze.moviescatalog.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRemoteDataSource(private val apiService: ApiService) : IMovieRemoteSource {
    override fun getGenreList(apiKey: String): Flow<ApiResponse<List<GenreResponse>>> {
        return flow {
            try {
                val response = apiService.getGenreList(apiKey)
                if (response.genres.isNotEmpty()){
                    emit(ApiResponse.Success(response.genres))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getMovieList(apiKey: String, withGenres: String, page: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList(apiKey, withGenres, page)
                if (response.results.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getMovieDetail(apiKey: String, idMovie: String): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(idMovie, apiKey)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getReview(apiKey: String, idMovie: String, page: String): Flow<ApiResponse<ReviewsResponse>> {
        return flow {
            try {
                val response = apiService.getReview(idMovie, apiKey, page)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getTrailer(apiKey: String, idMovie: String): Flow<ApiResponse<TrailersResponse>> {
        return flow {
            try {
                val response = apiService.getTrailer(idMovie, apiKey)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}