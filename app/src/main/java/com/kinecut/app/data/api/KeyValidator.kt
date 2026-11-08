package com.kinecut.app.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class KeyValidator(private val client: OkHttpClient = OkHttpClient()) {

    sealed class KeyStatus {
        data object Valid : KeyStatus()
        data class Invalid(val reason: String) : KeyStatus()
        data class Unknown(val error: String) : KeyStatus()
    }

    suspend fun validateGroq(key: String): KeyStatus = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://api.groq.com/openai/v1/models")
                .header("Authorization", "Bearer $key")
                .build()
            val response = client.newCall(request).execute()
            when (response.code) {
                200 -> KeyStatus.Valid
                401 -> KeyStatus.Invalid("Invalid API key")
                else -> KeyStatus.Unknown("HTTP ${response.code}")
            }
        } catch (e: Exception) {
            KeyStatus.Unknown(e.message ?: "Unknown error")
        }
    }

    suspend fun validateGemini(key: String): KeyStatus = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1/models?key=$key")
                .build()
            val response = client.newCall(request).execute()
            when (response.code) {
                200 -> KeyStatus.Valid
                400, 403 -> KeyStatus.Invalid("Invalid API key")
                else -> KeyStatus.Unknown("HTTP ${response.code}")
            }
        } catch (e: Exception) {
            KeyStatus.Unknown(e.message ?: "Unknown error")
        }
    }
}
