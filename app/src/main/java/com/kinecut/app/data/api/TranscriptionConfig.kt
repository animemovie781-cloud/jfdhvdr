package com.kinecut.app.data.api

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptionConfig(
    val language: String = "en",
    val model: String = "whisper-large-v3-turbo",
    val enableTimestamps: Boolean = true,
    val enableWordTimestamps: Boolean = true,
    val temperature: Float = 0f,
    val chunkLengthSec: Int = 30
)
