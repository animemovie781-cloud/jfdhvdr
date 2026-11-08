package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Blueprint(
    val id: String,
    val name: String,
    val config: BlueprintConfig,
    val scenes: List<SceneData> = emptyList()
)
