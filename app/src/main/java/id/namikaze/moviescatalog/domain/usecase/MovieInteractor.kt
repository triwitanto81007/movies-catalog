package id.namikaze.moviescatalog.domain.usecase

import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.*
import id.namikaze.moviescatalog.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getGenreList(): Flow<Resource<List<Genre>>>  = movieRepository.getGenreList()

    override fun getMovieList(withGenres: Int, page: String): Flow<Resource<List<Movie>>> = movieRepository.getMovieList(withGenres, page)

    override fun getMovieDetail(idMovie: Int): Flow<Resource<MovieDetail>> = movieRepository.getMovieDetail(idMovie)

    override fun getReview(idMovie: Int, page: String): Flow<Resource<List<Review>>> = movieRepository.getReview(idMovie, page)

    override fun getTrailer(idMovie: Int): Flow<Resource<Trailer>> = movieRepository.getTrailer(idMovie)

}