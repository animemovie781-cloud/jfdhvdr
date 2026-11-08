package com.kinecut.app.ui.captions

import kotlinx.serialization.Serializable

@Serializable
data class WordOverride(
    val wordIndex: Int,
    val text: String? = null,
    val color: Long? = null,
    val scale: Float? = null,
    val bold: Boolean? = null
)
