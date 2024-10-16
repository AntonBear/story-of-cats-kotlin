package com.anton.story_of_cats_kotlin

import android.content.Context
import kotlinx.serialization.json.Json
import models.Novel
import models.Page

interface NovelRepository {
    fun fetchPage(userChoice: Int): Page?
}

class NovelRepositoryImpl(private val context: Context) : NovelRepository {
    private val novel: Novel by lazy {
        val resource = context.resources.openRawResource(R.raw.novel)
        val reader = resource.bufferedReader()
        val jsonText = reader.use { it.readText() }
        return@lazy Json.decodeFromString<Novel>(jsonText)
    }

    override fun fetchPage(userChoice: Int): Page? {
        return novel.pages.firstOrNull { page -> page.id == userChoice }
    }
}