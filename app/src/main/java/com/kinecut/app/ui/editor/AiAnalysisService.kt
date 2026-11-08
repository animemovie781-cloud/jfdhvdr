package com.kinecut.app.ui.editor

import com.kinecut.app.data.api.GeminiAnalyzer
import com.kinecut.app.data.api.GroqPhraseAnalyzer
import com.kinecut.app.data.api.GroqTranscriptAnalyzer
import com.kinecut.app.data.model.TranscriptAnalysis
import com.kinecut.app.data.model.WordData

class AiAnalysisService(
    private val groqApiKey: String? = null,
    private val geminiApiKey: String? = null
) {
    suspend fun analyze(words: List<WordData>): TranscriptAnalysis {
        return when {
            groqApiKey != null -> GroqTranscriptAnalyzer(groqApiKey).analyze(words)
            geminiApiKey != null -> {
                val analyzer = GeminiAnalyzer(geminiApiKey)
                TranscriptAnalysis(language = "en")
            }
            else -> TranscriptAnalysis(language = "en")
        }
    }
}
