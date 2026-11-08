package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BackgroundClip(
    val uri: String,
    val trimStartMs: Long = 0,
    val trimEndMs: Long = 0,
    val volume: Float = 1f,
    val speed: Float = 1f
)
