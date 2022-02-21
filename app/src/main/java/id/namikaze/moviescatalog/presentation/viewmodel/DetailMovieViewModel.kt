package id.namikaze.moviescatalog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.MovieDetail
import id.namikaze.moviescatalog.domain.model.Review
import id.namikaze.moviescatalog.domain.model.Trailer
import id.namikaze.moviescatalog.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private var _movieDetail = MutableLiveData<Resource<MovieDetail>>()
    val movieDetail: LiveData<Resource<MovieDetail>> = _movieDetail

    private var _review = MutableLiveData<Resource<List<Review>>>()
    val review: LiveData<Resource<List<Review>>> = _review

    private var _trailer = MutableLiveData<Resource<Trailer>>()
    val trailer: LiveData<Resource<Trailer>> = _trailer

    suspend fun getMovieDetail(idMovie: Int) {
        movieUseCase.getMovieDetail(idMovie).collect{
            _movieDetail.postValue(it)
        }
    }

    suspend fun getReview(idMovie: Int, page: String) {
        movieUseCase.getReview(idMovie, page).collect{
            _review.postValue(it)
        }
    }

    suspend fun getTrailer(idMovie: Int) {
        movieUseCase.getTrailer(idMovie).collect{
            _trailer.postValue(it)
        }
    }
}