package com.aait.rxdeep

import android.app.Application
/*import org.koin.android.ext.android.startKoin*/
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
import org.koin.core.context.startKoin
*/


class MyApp : Application() {
    companion object{
        lateinit var  APP:MyApp
    }
    override fun onCreate() {
        super.onCreate()
        APP=this
        startKoin {
            // declare used Android context
            androidContext(this@MyApp)
            // declare modules
            modules(appModule)
        }
/*startKoin(APP, listOf(appModule))*/

    }
}