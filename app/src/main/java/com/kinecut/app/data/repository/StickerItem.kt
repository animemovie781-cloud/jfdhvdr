package com.kinecut.app.data.repository

data class StickerItem(
    val id: String,
    val name: String,
    val assetPath: String,
    val category: String = "default"
)
