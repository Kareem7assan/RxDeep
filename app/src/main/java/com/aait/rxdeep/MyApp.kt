package com.aait.rxdeep

import android.app.Application
/*import org.koin.android.ext.android.startKoin*/
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : Application() {
    companion object{
        lateinit var  APP:MyApp
    }
    override fun onCreate() {
        super.onCreate()
        APP=this
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

    }
}