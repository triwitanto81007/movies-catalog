package id.namikaze.moviescatalog.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.namikaze.moviescatalog.data.MovieRepository
import id.namikaze.moviescatalog.data.source.remote.IMovieRemoteSource
import id.namikaze.moviescatalog.data.source.remote.MovieRemoteDataSource
import id.namikaze.moviescatalog.domain.repository.IMovieRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideRemoteSource(movieRemoteDataSource: MovieRemoteDataSource): IMovieRemoteSource
}