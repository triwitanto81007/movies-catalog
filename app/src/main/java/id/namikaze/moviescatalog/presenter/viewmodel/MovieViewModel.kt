package id.namikaze.moviescatalog.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.Genre
import id.namikaze.moviescatalog.domain.model.Movie
import id.namikaze.moviescatalog.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private var _movie = MutableLiveData<Resource<List<Movie>>>()
    val movie: LiveData<Resource<List<Movie>>> = _movie

    suspend fun getMovieList(apiKey: String, withGenres: Int) {
        movieUseCase.getMovieList(apiKey, withGenres).collect{
            _movie.postValue(it)
        }
    }
}