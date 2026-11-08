package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class KeyframeEasing {
    LINEAR, EASE_IN, EASE_OUT, EASE_IN_OUT, SPRING
}
