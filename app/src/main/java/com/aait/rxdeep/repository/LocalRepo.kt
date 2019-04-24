package com.aait.rxdeep.repository

import com.aait.rxdeep.MyApp.Companion.APP
import com.aait.rxdeep.local_db.AppDB
import com.aait.rxdeep.models.PostModel
import io.reactivex.Observable

class LocalRepo : RepoPosts {

    override fun getPosts(user_id:Int): Observable<List<PostModel>> {

        return Observable.fromCallable{
            AppDB.getInstance(APP.applicationContext).appDao().getPosts()
        }

    }

    override fun savePosts(posts:List<PostModel>) {
        AppDB.getInstance(APP.applicationContext).appDao().addPosts(posts)
    }
}