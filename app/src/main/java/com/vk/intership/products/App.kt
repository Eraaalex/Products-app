package com.vk.intership.products

import android.app.Application
import com.vk.intership.products.di.appModule
import com.vk.intership.products.di.networkModule
import com.vk.intership.products.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    lateinit var koinApp: KoinApplication
    override fun onCreate() {
        super.onCreate()
        koinApp = startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, repositoryModule, networkModule))
        }
    }
}