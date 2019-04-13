package com.aait.rxdeep.network

import com.aait.rxdeep.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroWeb {
    val ENDPOINT="https://jsonplaceholder.typicode.com/"
    val serviceApi: ServiceApi

    init {
        serviceApi = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .build()
            .create(ServiceApi::class.java)


    }
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            b.addInterceptor(interceptor)
        }
        return b.build()
    }
 }
