package com.kinecut.app.data.api

class OverlapChunker(
    private val chunkDurationMs: Long = 30_000,
    private val overlapMs: Long = 2_000
) {
    data class Chunk(
        val index: Int,
        val startMs: Long,
        val endMs: Long
    )

    fun createChunks(totalDurationMs: Long): List<Chunk> {
        val chunks = mutableListOf<Chunk>()
        var start = 0L
        var index = 0
        while (start < totalDurationMs) {
            val end = minOf(start + chunkDurationMs, totalDurationMs)
            chunks.add(Chunk(index, start, end))
            start += chunkDurationMs - overlapMs
            index++
        }
        return chunks
    }
}
