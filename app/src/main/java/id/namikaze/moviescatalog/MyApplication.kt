package id.namikaze.moviescatalog

import android.app.Application
import id.namikaze.moviescatalog.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //mendaftarkan modul modul yang ada didalamnya
        startKoin {
//            androidLogger(Level.NONE)
            //dengan androidContext semua fungsi di dalam module yang membutuhkan context sudah otomatis tercover.
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    localDataSourceModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}