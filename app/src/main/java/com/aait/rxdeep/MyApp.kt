package com.aait.rxdeep

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class MyApp : Application() {
    companion object{
        lateinit var  APP:MyApp
    }
    override fun onCreate() {
        super.onCreate()
        APP=this
        startKoin(applicationContext,listOf(appModule))

    }
}