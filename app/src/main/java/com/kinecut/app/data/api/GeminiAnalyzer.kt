package com.kinecut.app.data.api

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.kinecut.app.BuildConfig
import com.kinecut.app.data.model.WordData
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.cancellation.CancellationException

private const val TAG = "GeminiAnalyzer"

class GeminiAnalyzer {

    sealed class GeminiResult<out T> {
        data class Success<T>(val data: T) : GeminiResult<T>()
        data class Error(val message: String) : GeminiResult<Nothing>()
        data object Empty : GeminiResult<Nothing>()
    }

    private val phraseModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
            }
        )
    }

    private val emphasisModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
            }
        )
    }

    suspend fun analyzePhrases(words: List<WordData>): GeminiResult<List<PhraseResult>> {
        if (words.isEmpty()) return GeminiResult.Empty
        
        val transcript = words.joinToString(" ") { it.text }
        val prompt = """
            Analyze the following transcript and group words into meaningful short phrases (2-5 words each) for video captions.
            Return the result as JSON with a single key "phrases" which is an array of objects.
            Each object must have "startIndex" (integer), "endIndex" (integer), and "text" (string).
            The indices are zero-based relative to the following word list:
            ${words.mapIndexed { index, word -> "$index: ${word.text}" }.joinToString(", ")}
            
            Transcript: $transcript
        """.trimIndent()

        return try {
            val response = withTimeout(30_000L) {
                phraseModel.generateContent(prompt)
            }
            val jsonString = response.text?.replace("```json", "")?.replace("```", "")?.trim() 
                ?: throw IllegalStateException("Empty response")
            val json = JSONObject(jsonString)
            val phrasesArray = json.getJSONArray("phrases")
            val results = mutableListOf<PhraseResult>()
            for (i in 0 until phrasesArray.length()) {
                val item = phrasesArray.getJSONObject(i)
                results.add(
                    PhraseResult(
                        item.getInt("startIndex"),
                        item.getInt("endIndex"),
                        item.getString("text")
                    )
                )
            }
            GeminiResult.Success(results)
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Phrase analysis timed out", e)
            GeminiResult.Error("Analysis timed out")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Phrase analysis failed", e)
            GeminiResult.Error(e.localizedMessage ?: "Analysis failed")
        }
    }

    suspend fun analyzeEmphasis(words: List<WordData>): GeminiResult<List<EmphasisResult>> {
        if (words.isEmpty()) return GeminiResult.Empty

        val transcript = words.joinToString(" ") { it.text }
        val prompt = """
            Analyze the following transcript and identify words that should be visually emphasized (e.g., keywords, emotional words, or stressed words).
            Return the result as JSON with a single key "emphasis" which is an array of objects.
            Each object must have "wordIndex" (integer) and "strength" (double, between 0.5 and 1.0).
            The indices are zero-based relative to the following word list:
            ${words.mapIndexed { index, word -> "$index: ${word.text}" }.joinToString(", ")}
            
            Transcript: $transcript
        """.trimIndent()

        return try {
            val response = withTimeout(30_000L) {
                emphasisModel.generateContent(prompt)
            }
            val jsonString = response.text?.replace("```json", "")?.replace("```", "")?.trim()
                ?: throw IllegalStateException("Empty response")
            val json = JSONObject(jsonString)
            val emphasisArray = json.getJSONArray("emphasis")
            val results = mutableListOf<EmphasisResult>()
            for (i in 0 until emphasisArray.length()) {
                val item = emphasisArray.getJSONObject(i)
                results.add(
                    EmphasisResult(
                        item.getInt("wordIndex"),
                        item.getDouble("strength").toFloat()
                    )
                )
            }
            GeminiResult.Success(results)
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Emphasis analysis timed out", e)
            GeminiResult.Error("Analysis timeout")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Emphasis analysis failed", e)
            GeminiResult.Error(e.localizedMessage ?: "Analysis failed")
        }
    }

    data class PhraseResult(val startIndex: Int, val endIndex: Int, val text: String)
    data class EmphasisResult(val wordIndex: Int, val strength: Float)
}
