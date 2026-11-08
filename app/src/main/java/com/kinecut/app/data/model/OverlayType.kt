package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class OverlayType {
    TEXT, EMOJI, STICKER, IMAGE, SHAPE
}
