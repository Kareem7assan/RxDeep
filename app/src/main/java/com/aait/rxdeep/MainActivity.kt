package com.aait.rxdeep

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aait.rxdeep.models.PostModel
import com.aait.rxdeep.network.RetroWeb
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        * create observable just when create it
        * doens't delay to subscribe to it
        *  so it require defered
        **/

        rxWithRetrofitNoObservable()
    }

    @SuppressLint("CheckResult")
    fun rxWithRetrofit(){
        Observable.defer {
            RetroWeb.serviceApi.posts(1)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("result",Gson().toJson(it))
            },{
                Log.e("error",it.message)
            },{
                Toast.makeText(applicationContext,"completed",Toast.LENGTH_SHORT).show()
            }
            )
    }

    @SuppressLint("CheckResult")
    fun rxWithRetrofitNoObservable(){
        Observable.fromCallable {
            RetroWeb.serviceApi.posts().execute()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("result",Gson().toJson(it.body()))
            },{
                Log.e("error",it.message)
            },{
                Toast.makeText(applicationContext,"completed",Toast.LENGTH_SHORT).show()
            }
            )
    }

    @SuppressLint("CheckResult")
    fun rxWithFilterMap(){
        Observable.just(Students(2,"Engineer"))
            .flatMap {
                getName(it.id)
            }
            .subscribe {
                Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show()
            }
    }

    fun getName(id:Int):Observable<String>{
        val db=HashMap<Int,String>()
        db[1] = "ali"
        db[2] = "kareem"
        db[3] = "omar"

        return Observable.just(db[id].orEmpty())
    }
    data class Students(var id:Int, var job:String)

    @SuppressLint("CheckResult")
    fun rxZip(){
        val progressDialog = ProgressDialog(this@MainActivity)

        Observable.zip(multiRx(), sumRx(), BiFunction<Int,Int,Int> { t1, t2 ->
            t1-t2
        })

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delaySubscription(3000,TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progressDialog.show()
                Log.e("dialog","start")
            }
            .subscribe({
                Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_SHORT).show()},
                {Log.e("error","asd")},
                {
                    progressDialog.hide()
                    Toast.makeText(applicationContext,"asd",Toast.LENGTH_SHORT).show()
                })
    }

    fun sumRx(): Observable<Int> {
      return  Observable.defer {
            Observable.just(2+3)
        }
    }

    fun multiRx(): Observable<Int> {
       return Observable.defer {
            Observable.just(2*3)
        }
    }
    @SuppressLint("CheckResult")
    fun rxFrom(){
        var arr= listOf<Int>(1,2,3)
        Observable.defer {
            Observable.fromIterable(arr)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.e("onSubscribe", "subscribed")
            }
            .filter{
                it>1
            }
            .subscribe({
                Log.e("onNext", it.toString())
            }, {
                Log.e("onError", it.message)
            }, {
                Log.e("onFinish", "completed")
            })


    }

    @SuppressLint("CheckResult")
    fun rxJust(){
         Observable.defer {
            Observable.just("asd")
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.e("onSubscribe", "subscribed")
            }
            .doOnNext {
                Log.e("doOnNext", it)
            }
            .subscribe({
                Log.e("onNext", it)
            }, {
                Log.e("onError", it.message)
            }, {
                Log.e("onFinish", "completed")
            })


    }
    @SuppressLint("CheckResult")
    fun callableRX(){

        //callable doesn't require a observable to do some thing
        Observable.fromCallable(object : Callable<String> {
            override fun call(): String {
                return "hello rx"
            }

        })

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.e("onSubscribe","subscribed")
            }
            .doOnNext {
                Log.e("doOnNext",it)
            }
            .subscribe({
                Log.e("onNext",it)
            },{
                Log.e("onError",it.message)
            },{
                Log.e("onFinish","completed")
            })
    }
}
