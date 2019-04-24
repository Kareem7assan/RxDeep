package com.aait.rxdeep.repository

import android.content.Context
import android.util.Log
import com.aait.rxdeep.MyApp.Companion.APP
import com.aait.rxdeep.local_db.AppDB
import com.aait.rxdeep.models.PostModel
import io.reactivex.Observable

class LocalRepo : RepoPosts {

    override fun getPosts(user_id:Int): Observable<List<PostModel>> {

        return Observable.fromCallable{
            Log.e("db",AppDB.getInstance(APP.applicationContext).appDao().getPosts().size.toString())
            AppDB.getInstance(APP.applicationContext).appDao().getPosts()
        }

    }

    override fun savePosts(posts:List<PostModel>) {
        Log.e("db","added")
        AppDB.getInstance(APP.applicationContext).appDao().addPosts(posts)
    }
}