package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OverlayAnimation(
    val enterPreset: OverlayEnterPreset = OverlayEnterPreset.NONE,
    val exitPreset: OverlayExitPreset = OverlayExitPreset.NONE,
    val emphasisPreset: OverlayEmphasisPreset = OverlayEmphasisPreset.NONE,
    val loopMode: KeyframeLoopMode = KeyframeLoopMode.NONE
)
