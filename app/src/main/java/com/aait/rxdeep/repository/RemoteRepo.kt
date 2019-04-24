package com.aait.rxdeep.repository

import com.aait.rxdeep.models.PostModel
import com.aait.rxdeep.network.RetroWeb
import io.reactivex.Observable

class RemoteRepo :RepoPosts{
    override fun getPosts(user_id:Int): Observable<List<PostModel>> {
        return RetroWeb.serviceApi.posts(user_id)
    }

    override fun savePosts(posts:List<PostModel>) {

    }
}