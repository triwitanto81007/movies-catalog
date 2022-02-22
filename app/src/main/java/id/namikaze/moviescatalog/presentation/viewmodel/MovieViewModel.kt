package id.namikaze.moviescatalog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.Movie
import id.namikaze.moviescatalog.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private var _movie = MutableLiveData<Resource<List<Movie>>>()
    val movie: LiveData<Resource<List<Movie>>> = _movie

    private var _search = MutableLiveData<Resource<List<Movie>>>()
    val search: LiveData<Resource<List<Movie>>> = _search

    suspend fun getMovieList(withGenres: Int, page: String) {
        movieUseCase.getMovieList(withGenres, page).collect{
            _movie.postValue(it)
        }
    }

    suspend fun getSearchMovieList(query: String, page: String) {
        movieUseCase.getSearchMovieList(query, page).collect{
            _search.postValue(it)
        }
    }
}