package com.kinecut.app.ui.editor

enum class GenerateStage {
    IDLE,
    EXTRACTING_AUDIO,
    TRANSCRIBING,
    ANALYZING,
    STYLING,
    RENDERING,
    COMPLETE,
    ERROR
}
