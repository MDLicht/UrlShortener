package com.mdlicht.zb.simpleurlshortener.common

import android.app.Application
import com.mdlicht.zb.simpleurlshortener.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            fileProperties()
            modules(listOf(appModule))
        }
    }
}