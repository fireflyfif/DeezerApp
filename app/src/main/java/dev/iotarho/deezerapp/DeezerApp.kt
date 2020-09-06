package dev.iotarho.deezerapp

import android.app.Application
import dev.iotarho.deezerapp.di.AppModules.searchModule
import dev.iotarho.deezerapp.di.AppModules.retrofitModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class DeezerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // All the modules added inside the startKoin {} will not initialize eagerly instead
        // Koin will create instance lazily only when they are needed.
        startKoin {
            // declare used Android context
            this.androidContext(applicationContext)
            modules(searchModule)
            modules(retrofitModule)
        }
    }
}