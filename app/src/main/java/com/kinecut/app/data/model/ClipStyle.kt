package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ClipStyle(
    val fitMode: FitMode = FitMode.FIT,
    val backgroundKind: BackgroundKind = BackgroundKind.BLUR
)
