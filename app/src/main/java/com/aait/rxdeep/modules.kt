package com.aait.rxdeep

import com.aait.rxdeep.repository.RepoPostsImp
import org.koin.dsl.module.module

val appModule = module {
    single {RepoPostsImp(get(),get()) }
}