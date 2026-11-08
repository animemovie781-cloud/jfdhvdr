package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BlueprintConfig(
    val width: Int = 1080,
    val height: Int = 1920,
    val fps: Int = 30,
    val durationMs: Long = 0
)
