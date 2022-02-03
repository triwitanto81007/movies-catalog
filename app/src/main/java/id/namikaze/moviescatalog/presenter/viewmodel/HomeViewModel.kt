package id.namikaze.moviescatalog.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.domain.usecase.RecipeUseCase
import kotlinx.coroutines.flow.collect

class HomeViewModel(private val recipeUseCase: RecipeUseCase) : ViewModel() {

    private var _recipe = MutableLiveData<Resource<List<Recipe>>>()
    val recipe: LiveData<Resource<List<Recipe>>> = _recipe

    suspend fun getRecipesList() {
        recipeUseCase.getRecipesList().collect{
            _recipe.postValue(it)
        }
    }
}