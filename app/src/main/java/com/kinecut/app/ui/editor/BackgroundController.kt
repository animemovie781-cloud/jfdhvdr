package com.kinecut.app.ui.editor

import com.kinecut.app.data.model.BackgroundKind
import com.kinecut.app.data.model.BackgroundPalette
import com.kinecut.app.data.model.BgSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BackgroundController {
    private val _settings = MutableStateFlow(BgSettings())
    val settings: StateFlow<BgSettings> = _settings.asStateFlow()

    fun setKind(kind: BackgroundKind) { _settings.value = _settings.value.copy(kind = kind) }
    fun setPalette(palette: BackgroundPalette) { _settings.value = _settings.value.copy(palette = palette) }
    fun setBlurRadius(radius: Float) { _settings.value = _settings.value.copy(blurRadius = radius) }
}
