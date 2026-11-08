package com.kinecut.app.scriptvoice.gemini

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiVoiceClient(private val apiKey: String) {

    class FieldShapeError(message: String) : Exception(message)

    data class VoiceRequest(
        val text: String,
        val voicePreset: String = "default",
        val language: String = "en"
    )

    data class VoiceResponse(
        val audioBase64: String,
        val durationMs: Long
    )

    suspend fun synthesize(request: VoiceRequest): VoiceResponse = withContext(Dispatchers.IO) {
        VoiceResponse("", 0)
    }
}
