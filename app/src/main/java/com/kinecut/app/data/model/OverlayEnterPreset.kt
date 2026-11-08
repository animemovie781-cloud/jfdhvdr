package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class OverlayEnterPreset {
    NONE, FADE_IN, SLIDE_UP, SLIDE_DOWN, SLIDE_LEFT, SLIDE_RIGHT, SCALE_UP, BOUNCE
}
