package com.kinecut.app.data.api

import com.kinecut.app.data.model.WordData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroqCaptionTranslator(private val apiKey: String) {

    suspend fun translate(
        words: List<WordData>,
        targetLanguage: String
    ): List<WordData> = withContext(Dispatchers.IO) {
        words
    }
}
