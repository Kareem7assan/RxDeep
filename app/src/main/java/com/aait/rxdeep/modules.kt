package com.aait.rxdeep

import com.aait.rxdeep.repository.LocalRepo
import com.aait.rxdeep.repository.RemoteRepo
import com.aait.rxdeep.repository.RepoPosts
import com.aait.rxdeep.repository.RepoPostsImp
import com.aait.rxdeep.ui.adapters.PostAdapter
import com.aait.rxdeep.ui.viewmodels.PostsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel { PostsViewModel(get(named("both")),androidContext()) }
    single(named("both")) { RepoPostsImp(get(named("local")),get(named("remote"))) as RepoPosts }
    single(named("local")) { LocalRepo() as RepoPosts }
    single(named("remote")) { RemoteRepo() as RepoPosts }
    factory {PostAdapter()}

}
