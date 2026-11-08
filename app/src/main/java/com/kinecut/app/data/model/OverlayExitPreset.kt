package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class OverlayExitPreset {
    NONE, FADE_OUT, SLIDE_UP, SLIDE_DOWN, SLIDE_LEFT, SLIDE_RIGHT, SCALE_DOWN
}
