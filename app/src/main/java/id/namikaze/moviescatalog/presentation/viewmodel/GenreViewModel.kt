package id.namikaze.moviescatalog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.model.Genre
import id.namikaze.moviescatalog.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private var _genre = MutableLiveData<Resource<List<Genre>>>()
    val genre: LiveData<Resource<List<Genre>>> = _genre

    suspend fun getGenreList() {
        //collect -> terminal operator (operator terakhir) untuk mengambil hasil akhir data
        movieUseCase.getGenreList().collect{
            _genre.postValue(it)
        }
    }


}