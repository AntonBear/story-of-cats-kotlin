package com.anton.story_of_cats_kotlin

import android.content.Context
import kotlinx.serialization.json.Json
import models.Novel

interface NovelRepository {
    val novel: Novel
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

//        context
//            .resources
//            .openRawResource(R.raw.novel)
//            .bufferedReader()
//            .use { it.readText() }
//            .let(Json::decodeFromString)
    }
}