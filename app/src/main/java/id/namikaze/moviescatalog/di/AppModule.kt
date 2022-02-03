package id.namikaze.moviescatalog.di

import id.namikaze.moviescatalog.domain.usecase.RecipeInteractor
import id.namikaze.moviescatalog.domain.usecase.RecipeUseCase
import id.namikaze.moviescatalog.presenter.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipeUseCase> { RecipeInteractor(get()) }
}

val viewModelModule =  module {
    viewModel { HomeViewModel(get()) }
}