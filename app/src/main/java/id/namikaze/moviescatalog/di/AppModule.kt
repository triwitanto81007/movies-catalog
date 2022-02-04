package id.namikaze.moviescatalog.di

import id.namikaze.moviescatalog.domain.usecase.MovieInteractor
import id.namikaze.moviescatalog.domain.usecase.MovieUseCase
import id.namikaze.moviescatalog.presenter.viewmodel.DetailMovieViewModel
import id.namikaze.moviescatalog.presenter.viewmodel.GenreViewModel
import id.namikaze.moviescatalog.presenter.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule =  module {
    viewModel { GenreViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }

}