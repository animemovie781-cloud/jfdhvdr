package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OverlayKeyframe(
    val timeMs: Long,
    val x: Float = 0f,
    val y: Float = 0f,
    val scale: Float = 1f,
    val rotation: Float = 0f,
    val opacity: Float = 1f,
    val easing: KeyframeEasing = KeyframeEasing.LINEAR,
    val coordMode: KeyframeCoordMode = KeyframeCoordMode.ABSOLUTE
)
