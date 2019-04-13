package com.aait.rxdeep.network

import com.aait.rxdeep.models.PostModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//service api
interface ServiceApi {

    @GET("posts")
    fun posts(
        @Query("user_id") user_id: Int
    ): Observable<List<PostModel>>

    @GET("posts")
    fun posts(
    ): Call<List<PostModel>>
}