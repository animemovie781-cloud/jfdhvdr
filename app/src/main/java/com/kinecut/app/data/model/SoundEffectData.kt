package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SoundEffectData(
    val id: String,
    val name: String,
    val assetPath: String,
    val startMs: Long,
    val volume: Float = 1f
)
