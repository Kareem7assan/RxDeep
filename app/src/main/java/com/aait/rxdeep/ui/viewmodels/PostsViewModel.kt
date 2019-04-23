package com.aait.rxdeep.ui.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.app.usage.NetworkStats
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aait.rxdeep.network.Resource
import com.aait.rxdeep.repository.LocalRepo
import com.aait.rxdeep.repository.RemoteRepo
import com.aait.rxdeep.repository.RepoPostsImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.aait.rxdeep.models.PostModel as PostModel1

class PostsViewModel(application: Application/*application: Application,*//*private val repoImp:RepoPostsImp*/) :
    AndroidViewModel( application) {
    private var repoImp: RepoPostsImp
    // private val repoImp:RepoPostsImp by inject()
    private val compositeDisposable = CompositeDisposable()
    var states: MutableLiveData<Resource<Any>>? = MutableLiveData()

    init {
        repoImp = RepoPostsImp(LocalRepo,RemoteRepo)
    }


    @SuppressLint("CheckResult")
    fun getPosts(userId:Int){
        Log.e("work","request")
        compositeDisposable.add(
        repoImp.getPosts(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                states?.postValue(Resource.loading())
            }
            .subscribe({
                states?.value=Resource.success(it,false)
            },{
                states?.value=Resource.error(it?.message!!,it,false)
                Log.e("error",it.message)
            },{
                Log.e("work","complete")
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}