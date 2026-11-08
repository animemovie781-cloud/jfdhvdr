package com.kinecut.app.speech.gemini

import com.kinecut.app.data.api.TranscriptionConfig
import java.io.File

class GeminiSpeechEngine(private val apiKey: String) {
    class SkipSignal : Exception("Skipped by user")

    suspend fun synthesize(text: String, language: String = "en"): ByteArray {
        return ByteArray(0)
    }
}
