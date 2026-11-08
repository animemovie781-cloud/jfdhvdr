package com.kinecut.app.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class GapFillTranscriber(
    private val groqService: GroqApiService,
    private val config: TranscriptionConfig = TranscriptionConfig()
) {
    data class Gap(val startMs: Long, val endMs: Long)
    data class Window(val startMs: Long, val endMs: Long, val words: List<WhisperWord>)

    class GroqHttpException(val code: Int, message: String) : Exception(message)

    companion object {
        fun planWindows(gaps: List<Gap>, windowSizeMs: Long = 30_000): List<Window> {
            return gaps.sortedBy { it.startMs }.map { gap ->
                Window(gap.startMs, minOf(gap.startMs + windowSizeMs, gap.endMs), emptyList())
            }
        }
    }

    suspend fun transcribe(audioFile: File): TranscriptionResponse =
        groqService.transcribeWithRetry(audioFile, config)

    suspend fun fillWindow(
        audioFile: File,
        window: Window
    ): List<WhisperWord> = withContext(Dispatchers.IO) {
        val response = groqService.transcribe(audioFile, config)
        response.segments.flatMap { it.words }
    }

    suspend fun transcribeBytesWithRetry(
        audioBytes: ByteArray,
        maxRetries: Int = 3
    ): TranscriptionResponse {
        val tempFile = File.createTempFile("gap_fill", ".wav")
        try {
            tempFile.writeBytes(audioBytes)
            return groqService.transcribeWithRetry(tempFile, config, maxRetries)
        } finally {
            tempFile.delete()
        }
    }

    fun applyHinglish(words: List<WhisperWord>, romanized: List<String>): List<WhisperWord> {
        return words.mapIndexed { index, word ->
            word.copy(word = romanized.getOrElse(index) { word.word })
        }
    }

    fun convertToHinglishAligned(words: List<WhisperWord>): List<WhisperWord> = words
}
