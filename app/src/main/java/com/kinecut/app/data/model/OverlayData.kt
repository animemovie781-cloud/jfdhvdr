package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OverlayData(
    val id: String,
    val type: OverlayType,
    val text: String = "",
    val x: Float = 0.5f,
    val y: Float = 0.5f,
    val scale: Float = 1f,
    val rotation: Float = 0f,
    val startMs: Long = 0,
    val endMs: Long = 0,
    val animation: OverlayAnimation? = null,
    val keyframes: List<OverlayKeyframe> = emptyList()
)
