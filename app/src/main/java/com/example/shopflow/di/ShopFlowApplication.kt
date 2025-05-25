package com.example.shopflow.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Custom [Application] class for the ShopFlow application.
 *
 * This class is responsible for initializing Koin for dependency injection
 * when the application starts.
 */
class ShopFlowApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ShopFlowApplication)
            modules(appModule)
        }
    }
}