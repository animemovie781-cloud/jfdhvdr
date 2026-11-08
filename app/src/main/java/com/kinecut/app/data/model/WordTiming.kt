package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WordTiming(
    val word: String,
    val startSec: Float,
    val endSec: Float
)
