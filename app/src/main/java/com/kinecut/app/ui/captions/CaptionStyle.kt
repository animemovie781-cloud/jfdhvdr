package com.kinecut.app.ui.captions

import kotlinx.serialization.Serializable

@Serializable
data class CaptionStyle(
    val fontFamily: String = "Inter",
    val fontSize: Float = 24f,
    val fontColor: Long = 0xFFFFFFFF,
    val backgroundColor: Long = 0x80000000,
    val strokeColor: Long = 0xFF000000,
    val strokeWidth: Float = 2f,
    val shadowEnabled: Boolean = true,
    val animationStyle: String = "karaoke",
    val wordsPerLine: Int = 4,
    val position: Float = 0.8f
)
