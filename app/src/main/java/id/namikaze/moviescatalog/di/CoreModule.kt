package id.namikaze.moviescatalog.di

import androidx.room.Room
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.data.MovieRepository
import id.namikaze.moviescatalog.data.source.local.IMovieLocalSource
import id.namikaze.moviescatalog.data.source.local.MovieLocalDataSource
import id.namikaze.moviescatalog.data.source.local.room.MovieDatabase
import id.namikaze.moviescatalog.data.source.remote.IMovieRemoteSource
import id.namikaze.moviescatalog.data.source.remote.MovieRemoteDataSource
import id.namikaze.moviescatalog.data.source.remote.network.ApiService
import id.namikaze.moviescatalog.domain.repository.IMovieRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    //setiap kali dibutuhkan akan membuat instance baru
    factory {
        get<MovieDatabase>().movieDao()
    }
    //membuat object selalu hidup selama aplikasi berjalan (singleton)
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.ENCRYPT_PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, BuildConfig.DATABSE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}${BuildConfig.API_PATH}")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val localDataSourceModule = module {
    single<IMovieLocalSource> {
        MovieLocalDataSource(get())
    }
}
val remoteDataSourceModule = module {
    single<IMovieRemoteSource> {
        MovieRemoteDataSource(get())
    }
}

val repositoryModule = module {
    single { MovieLocalDataSource(get()) }
    single { MovieRemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}