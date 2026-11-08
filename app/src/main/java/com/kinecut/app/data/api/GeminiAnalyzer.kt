package com.kinecut.app.data.api

import com.kinecut.app.data.model.WordData

class GeminiAnalyzer(private val apiKey: String) {

    sealed class GeminiResult<out T> {
        data class Success<T>(val data: T) : GeminiResult<T>()
        data class Error(val message: String) : GeminiResult<Nothing>()
        data object Empty : GeminiResult<Nothing>()
    }

    suspend fun analyzePhrases(words: List<WordData>): GeminiResult<List<PhraseResult>> {
        return GeminiResult.Success(emptyList())
    }

    suspend fun analyzeEmphasis(words: List<WordData>): GeminiResult<List<EmphasisResult>> {
        return GeminiResult.Success(emptyList())
    }

    data class PhraseResult(val startIndex: Int, val endIndex: Int, val text: String)
    data class EmphasisResult(val wordIndex: Int, val strength: Float)
}
