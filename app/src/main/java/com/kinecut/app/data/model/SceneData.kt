package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SceneData(
    val id: String,
    val startMs: Long,
    val endMs: Long,
    val clipGroups: List<ClipGroup> = emptyList(),
    val overlays: List<OverlayData> = emptyList()
)
