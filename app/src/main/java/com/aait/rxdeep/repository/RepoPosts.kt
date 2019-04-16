package com.aait.rxdeep.repository

import com.aait.rxdeep.models.PostModel
import io.reactivex.Observable
import retrofit2.Call

interface RepoPosts {
    fun getPosts(user_id:Int): Observable<List<PostModel>>
    fun savePosts(posts:List<PostModel>)
}
