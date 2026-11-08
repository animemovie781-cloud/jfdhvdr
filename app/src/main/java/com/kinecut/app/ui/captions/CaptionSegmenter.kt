package com.kinecut.app.ui.captions

import com.kinecut.app.data.model.WordData

class CaptionSegmenter(private val wordsPerLine: Int = 4) {

    data class Cue(
        val words: List<WordData>,
        val startMs: Long,
        val endMs: Long
    )

    fun segment(words: List<WordData>): List<Cue> {
        return words.chunked(wordsPerLine).map { chunk ->
            Cue(
                words = chunk,
                startMs = chunk.first().startMs,
                endMs = chunk.last().endMs
            )
        }
    }
}
