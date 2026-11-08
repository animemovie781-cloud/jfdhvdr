package com.kinecut.app.ui.editor

import com.kinecut.app.data.api.GeminiAnalyzer
import com.kinecut.app.data.api.GroqPhraseAnalyzer
import com.kinecut.app.data.api.GroqTranscriptAnalyzer
import com.kinecut.app.data.model.TranscriptAnalysis
import com.kinecut.app.data.model.WordData

class AiAnalysisService(
    private val groqApiKey: String? = null
) {
    suspend fun analyze(words: List<WordData>): TranscriptAnalysis {
        return when {
            groqApiKey != null -> GroqTranscriptAnalyzer(groqApiKey).analyze(words)
            else -> {
                val analyzer = GeminiAnalyzer()
                val phrasesResult = analyzer.analyzePhrases(words)
                val emphasisResult = analyzer.analyzeEmphasis(words)
                
                val phrases = if (phrasesResult is GeminiAnalyzer.GeminiResult.Success) {
                    phrasesResult.data.map { com.kinecut.app.data.model.PhraseInfo(it.text, it.startIndex, it.endIndex) }
                } else emptyList()
                
                val emphasis = if (emphasisResult is GeminiAnalyzer.GeminiResult.Success) {
                    emphasisResult.data.map { com.kinecut.app.data.model.EmphasisInfo(it.wordIndex, it.strength) }
                } else emptyList()
                
                TranscriptAnalysis(language = "en", phrases = phrases, emphasis = emphasis)
            }
        }
    }
}
