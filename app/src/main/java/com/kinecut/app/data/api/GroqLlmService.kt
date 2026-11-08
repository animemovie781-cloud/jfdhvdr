package com.kinecut.app.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.concurrent.TimeUnit

class GroqLlmService(private val apiKey: String) {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .dns(Ipv4FirstDns())
        .build()

    private val gson = Gson()

    suspend fun convertToHinglish(text: String): String = withContext(Dispatchers.IO) {
        val body = JsonObject().apply {
            addProperty("model", GroqModels.LLAMA_3_1_8B)
            add("messages", gson.toJsonTree(listOf(
                mapOf("role" to "system", "content" to "Convert the given Hindi text to Hinglish (Hindi words in Roman script). Keep English words as-is."),
                mapOf("role" to "user", "content" to text)
            )))
            addProperty("temperature", 0.1)
        }

        val request = Request.Builder()
            .url("https://api.groq.com/openai/v1/chat/completions")
            .header("Authorization", "Bearer $apiKey")
            .post(body.toString().toRequestBody("application/json".toMediaType()))
            .build()

        val response = client.newCall(request).execute()
        val json = gson.fromJson(response.body?.string(), JsonObject::class.java)
        json.getAsJsonArray("choices")
            ?.get(0)?.asJsonObject
            ?.getAsJsonObject("message")
            ?.get("content")?.asString ?: text
    }
}
