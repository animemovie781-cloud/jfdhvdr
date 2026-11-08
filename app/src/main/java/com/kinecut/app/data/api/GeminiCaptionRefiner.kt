package com.kinecut.app.data.api

import com.kinecut.app.data.model.WordData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiCaptionRefiner(private val apiKey: String) {

    companion object {
        const val MAX_WORDS_PER_CALL = 500
    }

    suspend fun refine(words: List<WordData>): List<WordData> = withContext(Dispatchers.IO) {
        words
    }

    suspend fun callGeminiWithFallback(
        words: List<WordData>,
        primary: suspend (List<WordData>) -> List<WordData>,
        fallback: suspend (List<WordData>) -> List<WordData>
    ): List<WordData> {
        return try {
            primary(words)
        } catch (e: Exception) {
            fallback(words)
        }
    }
}
