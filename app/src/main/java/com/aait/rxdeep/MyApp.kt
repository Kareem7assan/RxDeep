package com.aait.rxdeep

import android.app.Application

class MyApp : Application() {
    companion object{
        lateinit var  APP:MyApp
    }
    override fun onCreate() {
        super.onCreate()
        APP=this
    }
}