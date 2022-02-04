package id.namikaze.moviescatalog.domain.usecase

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.*
import id.namikaze.moviescatalog.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getGenreList(apiKey: String): Flow<Resource<List<Genre>>>  = movieRepository.getGenreList(apiKey)

    override fun getMovieList(apiKey: String, withGenres: Int, page: String, limit: Int, offset: Int): Flow<Resource<List<Movie>>> = movieRepository.getMovieList(apiKey, withGenres, page, limit, offset)

    override fun getMovieDetail(apiKey: String, idMovie: Int): Flow<Resource<MovieDetail>> = movieRepository.getMovieDetail(apiKey, idMovie)

    override fun getReview(apiKey: String, idMovie: Int, page: String, limit: Int, offset: Int): Flow<Resource<List<Review>>> = movieRepository.getReview(apiKey, idMovie, page, limit, offset)

    override fun getTrailer(apiKey: String, idMovie: Int): Flow<Resource<Trailer>> = movieRepository.getTrailer(apiKey, idMovie)

}