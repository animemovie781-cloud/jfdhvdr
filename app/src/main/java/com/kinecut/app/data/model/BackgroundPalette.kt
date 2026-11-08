package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BackgroundPalette(
    val primary: Long = 0xFF000000,
    val secondary: Long = 0xFF1A1A2E,
    val accent: Long = 0xFF6200EE
)

fun BackgroundPalette.toColorList(): List<Long> = listOf(primary, secondary, accent)
