package com.kinecut.app.data.api

import kotlinx.serialization.Serializable

@Serializable
data class WhisperWord(
    val word: String,
    val start: Float,
    val end: Float
)
