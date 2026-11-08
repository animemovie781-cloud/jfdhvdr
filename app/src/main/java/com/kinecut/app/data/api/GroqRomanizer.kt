package com.kinecut.app.data.api

import com.kinecut.app.data.model.WordData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroqRomanizer(private val apiKey: String) {

    suspend fun romanize(words: List<WordData>): List<WordData> =
        withContext(Dispatchers.IO) { words }

    suspend fun convertAligned(
        words: List<WordData>,
        scheme: String = "hinglish"
    ): List<WordData> = withContext(Dispatchers.IO) { words }
}
