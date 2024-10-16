package com.anton.story_of_cats_kotlin

import org.koin.dsl.module

val appModule = module {
    single<NovelRepository> { NovelRepositoryImpl(get()) }
}