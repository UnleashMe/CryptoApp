package com.example.cryptoapp

import android.app.Application
import com.example.cryptoapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoApp)
            modules(appModule)
        }
    }
}