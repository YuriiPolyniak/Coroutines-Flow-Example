package com.flowpreviewapplication

import android.app.Application
import com.flowpreviewapplication.di.AppModule
import com.flowpreviewapplication.di.PokemonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(AppModule.module, PokemonModule.module)
        }
    }

}