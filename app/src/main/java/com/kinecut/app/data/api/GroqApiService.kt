package com.kinecut.app.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import com.google.gson.Gson
import java.io.File
import java.util.concurrent.TimeUnit

class GroqApiService(private val apiKey: String) {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .dns(Ipv4FirstDns())
        .build()

    private val gson = Gson()

    class GroqHttpException(val code: Int, message: String) : Exception(message)

    suspend fun transcribe(
        audioFile: File,
        config: TranscriptionConfig = TranscriptionConfig()
    ): TranscriptionResponse = withContext(Dispatchers.IO) {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("model", config.model)
            .addFormDataPart("language", config.language)
            .addFormDataPart("response_format", "verbose_json")
            .addFormDataPart("timestamp_granularities[]", "word")
            .addFormDataPart("timestamp_granularities[]", "segment")
            .addFormDataPart("temperature", config.temperature.toString())
            .addFormDataPart(
                "file",
                audioFile.name,
                audioFile.asRequestBody("audio/wav".toMediaType())
            )
            .build()

        val request = Request.Builder()
            .url("https://api.groq.com/openai/v1/audio/transcriptions")
            .header("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw GroqHttpException(
                response.code,
                response.body?.string() ?: "Transcription failed"
            )
        }
        gson.fromJson(response.body?.string(), TranscriptionResponse::class.java)
    }

    suspend fun transcribeChunked(
        audioFile: File,
        config: TranscriptionConfig = TranscriptionConfig(),
        chunker: OverlapChunker = OverlapChunker()
    ): TranscriptionResponse {
        // For single files, use direct transcription
        return transcribe(audioFile, config)
    }

    suspend fun transcribeWithRetry(
        audioFile: File,
        config: TranscriptionConfig = TranscriptionConfig(),
        maxRetries: Int = 3
    ): TranscriptionResponse {
        var lastError: Exception? = null
        repeat(maxRetries) {
            try {
                return transcribe(audioFile, config)
            } catch (e: Exception) {
                lastError = e
            }
        }
        throw lastError ?: Exception("Transcription failed after $maxRetries retries")
    }
}
