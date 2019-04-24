package com.aait.rxdeep

import com.aait.rxdeep.ui.adapters.PostAdapter
import com.aait.rxdeep.ui.viewmodels.PostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { PostsViewModel(get()) }
    factory {PostAdapter()}

}
