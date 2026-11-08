package com.kinecut.app.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class KeyframeLoopMode {
    NONE, LOOP, PING_PONG
}
