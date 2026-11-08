package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WordData(
    val text: String,
    val startMs: Long,
    val endMs: Long,
    val confidence: Float = 1f
)
