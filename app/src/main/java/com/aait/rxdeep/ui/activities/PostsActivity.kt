package com.aait.rxdeep.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aait.rxdeep.R
import com.aait.rxdeep.models.PostModel
import com.aait.rxdeep.network.Resource
import com.aait.rxdeep.ui.adapters.PostAdapter
import com.aait.rxdeep.ui.viewmodels.PostsViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_posts.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/*import org.koin.androidx.viewmodel.ext.android.viewModel*/

class PostsActivity : AppCompatActivity() {
    val adapter:PostAdapter by inject()
    private val USER_ID: Int=1
    val viewModel:PostsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        setupRec()
        /*viewModel=getViewModel()*/
        //val viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewModel.getPosts(USER_ID)
        viewModel.states!!.observe(this, Observer {
            when{
                it.status==Resource.Status.LOADING->{
                    Log.e("error","loading")
                }
                it.status==Resource.Status.SUCCESS->{
                    Log.e("data",Gson().toJson(it.data as (List<*>)))
                    adapter.swapData(it.data as (List<PostModel>))
                    }
                it.status==Resource.Status.ERROR->{
                    Log.e("error",it.message)
                }
            }

        })

    }

    private fun setupRec() {

        rec_posts.layoutManager=LinearLayoutManager(this)
        rec_posts.adapter=adapter
    }
}
