package com.example.simpletodoapp

import android.app.Application
import com.example.simpletodoapp.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class SimpleToDoApp: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger()
            androidContext(this@SimpleToDoApp)
            modules(appModule)
        }
    }
}