package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WordLayout(
    val x: Float,
    val y: Float,
    val scale: Float = 1f,
    val rotation: Float = 0f
)
