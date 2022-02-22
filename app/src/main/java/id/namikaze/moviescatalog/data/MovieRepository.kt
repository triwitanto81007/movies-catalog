package id.namikaze.moviescatalog.data

import id.namikaze.moviescatalog.data.source.remote.IMovieRemoteSource
import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.response.*
import id.namikaze.moviescatalog.domain.model.*
import id.namikaze.moviescatalog.domain.repository.IMovieRepository
import id.namikaze.moviescatalog.utlis.DataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: IMovieRemoteSource,
): IMovieRepository {
    override fun getGenreList(): Flow<Resource<List<Genre>>> {
        return object: NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override suspend fun loadCallResult(data: List<GenreResponse>): List<Genre>{
                return DataMapper.genresMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> =
                movieRemoteDataSource.getGenreList()

        }.asFlow()
    }

    override fun getMovieList(withGenres: Int, page: String): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun loadCallResult(data: List<MovieResponse>): List<Movie> {
                return DataMapper.moviesMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                movieRemoteDataSource.getMovieList(withGenres.toString(), page)

        }.asFlow()
    }

    override fun getSearchMovieList(query: String, page: String): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun loadCallResult(data: List<MovieResponse>): List<Movie> {
                return DataMapper.moviesMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                movieRemoteDataSource.getSearchMovieList(query, page)

        }.asFlow()
    }

    override fun getMovieDetail(idMovie: Int): Flow<Resource<MovieDetail>> {
        return object: NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override suspend fun loadCallResult(data: MovieDetailResponse): MovieDetail {
                return DataMapper.movieDetailMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                movieRemoteDataSource.getMovieDetail(idMovie.toString())

        }.asFlow()
    }

    override fun getReview(idMovie: Int, page: String): Flow<Resource<List<Review>>> {
        return object: NetworkBoundResource<List<Review>, ReviewsResponse>() {
            override suspend fun loadCallResult(data: ReviewsResponse): List<Review> {
                return DataMapper.reviewMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ReviewsResponse>> =
                movieRemoteDataSource.getReview(idMovie.toString(), page)

        }.asFlow()
    }

    override fun getTrailer(idMovie: Int): Flow<Resource<Trailer>> {
        return object: NetworkBoundResource<Trailer, TrailersResponse>() {
            override suspend fun loadCallResult(data: TrailersResponse): Trailer {
                return DataMapper.trailerMapperResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<TrailersResponse>> =
                movieRemoteDataSource.getTrailer(idMovie.toString())

        }.asFlow()
    }
}