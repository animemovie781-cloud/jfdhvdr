package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptAnalysis(
    val language: String = "en",
    val phrases: List<PhraseInfo> = emptyList(),
    val emphasis: List<EmphasisInfo> = emptyList()
)

@Serializable
data class PhraseInfo(
    val text: String,
    val startWordIndex: Int,
    val endWordIndex: Int
)

@Serializable
data class EmphasisInfo(
    val wordIndex: Int,
    val strength: Float = 1f
)
