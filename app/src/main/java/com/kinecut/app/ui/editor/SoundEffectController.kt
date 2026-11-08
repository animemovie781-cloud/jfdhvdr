package com.kinecut.app.ui.editor

import com.kinecut.app.data.model.SoundEffectData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SoundEffectController {
    private val _effects = MutableStateFlow<List<SoundEffectData>>(emptyList())
    val effects: StateFlow<List<SoundEffectData>> = _effects.asStateFlow()

    fun add(effect: SoundEffectData) { _effects.value = _effects.value + effect }
    fun remove(id: String) { _effects.value = _effects.value.filter { it.id != id } }
    fun updateVolume(id: String, volume: Float) {
        _effects.value = _effects.value.map {
            if (it.id == id) it.copy(volume = volume) else it
        }
    }
}
