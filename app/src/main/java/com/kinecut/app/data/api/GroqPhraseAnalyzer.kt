package com.kinecut.app.data.api

import com.kinecut.app.data.model.WordData

class GroqPhraseAnalyzer(private val apiKey: String) {
    data class PhraseGroup(val startIndex: Int, val endIndex: Int)

    suspend fun analyze(words: List<WordData>): List<PhraseGroup> {
        return emptyList()
    }
}
