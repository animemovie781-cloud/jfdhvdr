package com.kinecut.app.ui.editor

import com.kinecut.app.data.model.OverlayData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OverlayController {
    private val _overlays = MutableStateFlow<List<OverlayData>>(emptyList())
    val overlays: StateFlow<List<OverlayData>> = _overlays.asStateFlow()

    fun add(overlay: OverlayData) { _overlays.value = _overlays.value + overlay }
    fun remove(id: String) { _overlays.value = _overlays.value.filter { it.id != id } }
    fun update(overlay: OverlayData) {
        _overlays.value = _overlays.value.map { if (it.id == overlay.id) overlay else it }
    }
}
