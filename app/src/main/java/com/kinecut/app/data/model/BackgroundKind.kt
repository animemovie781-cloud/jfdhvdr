package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class BackgroundKind {
    BLUR, COLOR, GRADIENT, IMAGE, NONE
}
