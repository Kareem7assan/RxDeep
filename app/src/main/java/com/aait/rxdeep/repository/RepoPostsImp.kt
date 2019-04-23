package com.aait.rxdeep.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.aait.rxdeep.models.PostModel
import io.reactivex.Observable

class RepoPostsImp(val localRepo:LocalRepo,val remoteRepo: RepoPosts) : RepoPosts {
    override fun getPosts(user_id:Int): Observable<List<PostModel>> {

        return Observable.concatArray(localRepo.getPosts(user_id),remoteRepo.getPosts(user_id))
            .doOnNext {
                savePosts(it)
            }

    }

    @SuppressLint("CheckResult")
    override fun savePosts(posts:List<PostModel>) {

        localRepo.savePosts(posts)

    }
}