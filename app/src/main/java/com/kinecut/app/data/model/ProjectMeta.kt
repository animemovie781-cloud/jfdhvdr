package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectMeta(
    val id: String,
    val name: String,
    val createdAt: Long,
    val lastModified: Long,
    val thumbnailPath: String? = null,
    val durationMs: Long = 0
)
