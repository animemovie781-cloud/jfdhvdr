package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EmphasisMark(
    val wordIndex: Int,
    val color: Long? = null,
    val scale: Float = 1.2f,
    val bold: Boolean = true
)
