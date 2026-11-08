package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BgSettings(
    val kind: BackgroundKind = BackgroundKind.BLUR,
    val palette: BackgroundPalette = BackgroundPalette(),
    val blurRadius: Float = 25f
)
