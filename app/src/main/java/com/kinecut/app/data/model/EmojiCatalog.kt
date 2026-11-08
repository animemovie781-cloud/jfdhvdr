package com.kinecut.app.data.model

data class EmojiCatalog(
    val categories: List<EmojiCategory> = emptyList()
)

data class EmojiCategory(
    val name: String,
    val emojis: List<String>
)
