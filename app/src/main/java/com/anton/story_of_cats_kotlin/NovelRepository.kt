package com.anton.story_of_cats_kotlin

import android.content.Context
import kotlinx.serialization.json.Json
import models.Novel
import models.Page

interface NovelRepositoryInt {
    val novel: Novel
    fun fetchPage(userChoice: Int): Page?
}

object NovelRepository : NovelRepositoryInt {
    private var _novel: Novel? = null
    private var isInitialized = false


    fun initialize(context: Context) {
        if (!isInitialized) {
            loadNovel(context)
            isInitialized = true
        }
    }

    private fun loadNovel(context: Context) {
        val resource = context.resources.openRawResource(R.raw.novel)
        val reader = resource.bufferedReader()
        val jsonText = reader.use { it.readText() }
        _novel = Json.decodeFromString<Novel>(jsonText)
    }

    override val novel: Novel
        get() = _novel ?: throw IllegalStateException("Novel not initialized")

    override fun fetchPage(userChoice: Int): Page? {
        return novel.pages.firstOrNull { page -> page.id == userChoice }
    }
}