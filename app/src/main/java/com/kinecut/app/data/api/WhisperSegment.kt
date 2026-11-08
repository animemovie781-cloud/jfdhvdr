package com.kinecut.app.data.api

import kotlinx.serialization.Serializable

@Serializable
data class WhisperSegment(
    val id: Int,
    val start: Float,
    val end: Float,
    val text: String,
    val words: List<WhisperWord> = emptyList()
)
