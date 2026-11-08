package com.kinecut.app.ui.editor

import com.kinecut.app.data.api.*
import com.kinecut.app.data.model.WordData
import java.io.File

class TranscriptionService(
    private val groqApiKey: String? = null,
    private val geminiApiKey: String? = null
) {
    suspend fun transcribe(
        audioFile: File,
        config: TranscriptionConfig = TranscriptionConfig()
    ): List<WordData> {
        val key = groqApiKey ?: geminiApiKey
            ?: throw com.kinecut.app.keyfree.NoAiKeyException("transcription")

        val response = if (groqApiKey != null) {
            GroqApiService(groqApiKey).transcribeWithRetry(audioFile, config)
        } else {
            GeminiTranscriber(geminiApiKey!!).transcribe(audioFile)
        }

        return response.segments.flatMap { segment ->
            segment.words.map { word ->
                WordData(
                    text = word.word.trim(),
                    startMs = (word.start * 1000).toLong(),
                    endMs = (word.end * 1000).toLong()
                )
            }
        }
    }
}
