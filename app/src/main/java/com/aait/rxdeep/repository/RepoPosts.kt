package com.aait.rxdeep.repository

import com.aait.rxdeep.models.PostModel
import retrofit2.Call

interface RepoPosts {
    fun getPosts(): Call<List<PostModel>>
    fun savePosts()
}
