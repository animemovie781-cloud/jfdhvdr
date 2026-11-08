package com.kinecut.app.ui.captions

import com.kinecut.app.data.model.WordData
import kotlinx.serialization.Serializable

@Serializable
data class CaptionProject(
    val id: String,
    val audioUri: String,
    val words: List<WordData> = emptyList(),
    val style: CaptionStyle = CaptionStyle(),
    val overrides: List<WordOverride> = emptyList()
)
