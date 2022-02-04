package id.namikaze.moviescatalog.data

import id.namikaze.moviescatalog.data.source.local.IMovieLocalSource
import id.namikaze.moviescatalog.data.source.remote.IMovieRemoteSource
import id.namikaze.moviescatalog.data.source.remote.network.ApiResponse
import id.namikaze.moviescatalog.data.source.remote.response.*
import id.namikaze.moviescatalog.domain.model.*
import id.namikaze.moviescatalog.domain.repository.IMovieRepository
import id.namikaze.moviescatalog.utlis.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val movieRemoteDataSource: IMovieRemoteSource,
    private val movieLocalDataSource: IMovieLocalSource
): IMovieRepository {
    override fun getGenreList(apiKey: String): Flow<Resource<List<Genre>>> {
        return object: NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override fun loadFromDB(): Flow<List<Genre>> {
                return movieLocalDataSource.getGenreList().map {
                    DataMapper.GenresMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Genre>?): Boolean  = true

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> =
                movieRemoteDataSource.getGenreList(apiKey)

            override suspend fun saveCallResult(data: List<GenreResponse>) {
                movieLocalDataSource.insertGenreList(
                    DataMapper.GenresMapper.mapResponsesToEntities(data)
                )
            }
        }.asFlow()
    }

    override fun getMovieList(apiKey: String, withGenres: Int, page: String, limit: Int, offset: Int): Flow<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return movieLocalDataSource.getMovieList(withGenres.toString(), limit, offset).map {
                    DataMapper.MoviesMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean  = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                movieRemoteDataSource.getMovieList(apiKey, withGenres.toString(), page)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                movieLocalDataSource.insertMovieList(
                    DataMapper.MoviesMapper.mapResponsesToEntities(data)
                )
            }
        }.asFlow()
    }

    override fun getMovieDetail(apiKey: String, idMovie: Int): Flow<Resource<MovieDetail>> {
        return object: NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                return movieLocalDataSource.getMovieDetail(idMovie).map {
                    DataMapper.MovieDetailMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: MovieDetail?): Boolean  = true

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                movieRemoteDataSource.getMovieDetail(apiKey, idMovie.toString())

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                movieLocalDataSource.insertMovieDetail(
                    DataMapper.MovieDetailMapper.mapResponsesToEntities(data)
                )
            }
        }.asFlow()
    }

    override fun getReview(apiKey: String, idMovie: Int, page: String, limit: Int, offset: Int): Flow<Resource<List<Review>>> {
        return object: NetworkBoundResource<List<Review>, ReviewsResponse>() {
            override fun loadFromDB(): Flow<List<Review>> {
                return movieLocalDataSource.getReview(idMovie, limit, offset).map {
                    DataMapper.ReviewMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Review>?): Boolean  = true

            override suspend fun createCall(): Flow<ApiResponse<ReviewsResponse>> =
                movieRemoteDataSource.getReview(apiKey, idMovie.toString(), page)

            override suspend fun saveCallResult(data: ReviewsResponse) {
                movieLocalDataSource.insertReview(
                    DataMapper.ReviewMapper.mapResponsesToEntities(data)
                )
            }
        }.asFlow()
    }

    override fun getTrailer(apiKey: String, idMovie: Int): Flow<Resource<Trailer>> {
        return object: NetworkBoundResource<Trailer, TrailersResponse>() {
            override fun loadFromDB(): Flow<Trailer> {
                return movieLocalDataSource.getTrailer(idMovie).map {
                    DataMapper.TrailerMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Trailer?): Boolean  = true

            override suspend fun createCall(): Flow<ApiResponse<TrailersResponse>> =
                movieRemoteDataSource.getTrailer(apiKey, idMovie.toString())

            override suspend fun saveCallResult(data: TrailersResponse) {
                if (data.results.size != 0){
                    movieLocalDataSource.insertTrailer(
                        DataMapper.TrailerMapper.mapResponsesToEntities(data)
                    )
                }
            }
        }.asFlow()
    }
}