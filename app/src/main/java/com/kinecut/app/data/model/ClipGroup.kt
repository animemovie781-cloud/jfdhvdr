package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ClipGroup(
    val id: String,
    val clips: List<BackgroundClip> = emptyList(),
    val style: ClipStyle = ClipStyle()
)
