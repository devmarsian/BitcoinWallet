package com.testtask.bitcoinwallet

import android.app.Application
import com.testtask.bitcoinwallet.di.appModule
import com.testtask.bitcoinwallet.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppClass: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
            modules(appModule, networkModule)
        }
    }
}