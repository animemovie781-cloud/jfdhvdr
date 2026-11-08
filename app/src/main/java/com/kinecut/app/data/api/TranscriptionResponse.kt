package com.kinecut.app.data.api

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptionResponse(
    val text: String,
    val segments: List<WhisperSegment> = emptyList(),
    val language: String = "en"
)
