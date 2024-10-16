package com.anton.story_of_cats_kotlin

import android.content.Context
import kotlinx.serialization.json.Json
import models.Novel
import models.Page

interface NovelRepository {
    val novel: Novel
    fun fetchPage(userChoice: Int): Page?
}

class NovelRepositoryImpl(
    private val context: Context
) : NovelRepository {
    override val novel: Novel by lazy {
        val resource = context.resources.openRawResource(R.raw.novel)
        val reader = resource.bufferedReader()
        val jsonText = reader.readText()
        reader.close()

        val novel = Json.decodeFromString<Novel>(jsonText)
        return@lazy novel
    }

    override fun fetchPage(userChoice: Int): Page? {
        return novel.pages.firstOrNull { page -> page.id == userChoice }
    }

}