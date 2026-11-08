package com.kinecut.app.data.api

import com.kinecut.app.data.model.TranscriptAnalysis
import com.kinecut.app.data.model.WordData

class GroqTranscriptAnalyzer(private val apiKey: String) {

    suspend fun analyze(words: List<WordData>): TranscriptAnalysis {
        return TranscriptAnalysis(language = "en")
    }
}
