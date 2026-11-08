package com.kinecut.app.ui.editor

import android.content.Context
import com.kinecut.app.data.repository.StickerItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StickerController(private val context: Context) {
    private val _stickers = MutableStateFlow<List<StickerItem>>(emptyList())
    val stickers: StateFlow<List<StickerItem>> = _stickers.asStateFlow()

    fun loadStickers() {
        val assets = context.assets.list("stickers") ?: emptyArray()
        _stickers.value = assets.map { name ->
            StickerItem(
                id = name,
                name = name.substringBeforeLast('.'),
                assetPath = "stickers/$name"
            )
        }
    }
}
