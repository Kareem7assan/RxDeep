package com.aait.rxdeep

import com.aait.rxdeep.network.Resource
import com.aait.rxdeep.repository.RepoPosts
import com.aait.rxdeep.repository.RepoPostsImp
import com.aait.rxdeep.ui.adapters.PostAdapter
import com.aait.rxdeep.ui.viewmodels.PostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module

val appModule = module {
   // viewModel { PostsViewModel(get()) }
    //single {RepoPostsImp(get(),get()) as RepoPosts}
    /*factory {PostAdapter()}*/

}