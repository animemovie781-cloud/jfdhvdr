package com.kinecut.app.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kinecut.app.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditorViewModel(application: Application) : AndroidViewModel(application) {

    sealed class ExportState {
        data object Idle : ExportState()
        data class Exporting(val progress: Float) : ExportState()
        data class Done(val path: String) : ExportState()
        data class Failed(val error: String) : ExportState()
    }

    data class WordDecoration(
        val wordIndex: Int,
        val color: Long? = null,
        val scale: Float = 1f,
        val bold: Boolean = false
    )

    private val _blueprint = MutableStateFlow<Blueprint?>(null)
    val blueprint: StateFlow<Blueprint?> = _blueprint.asStateFlow()

    private val _words = MutableStateFlow<List<WordData>>(emptyList())
    val words: StateFlow<List<WordData>> = _words.asStateFlow()

    private val _exportState = MutableStateFlow<ExportState>(ExportState.Idle)
    val exportState: StateFlow<ExportState> = _exportState.asStateFlow()

    private val _currentTimeMs = MutableStateFlow(0L)
    val currentTimeMs: StateFlow<Long> = _currentTimeMs.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    fun loadProject(projectId: String) {
        viewModelScope.launch {
            // Load project from repository
        }
    }

    fun setWords(words: List<WordData>) {
        _words.value = words
    }

    fun seekTo(timeMs: Long) {
        _currentTimeMs.value = timeMs
    }

    fun togglePlayback() {
        _isPlaying.value = !_isPlaying.value
    }

    fun addOverlay(overlay: OverlayData) {
        val current = _blueprint.value ?: return
        val scenes = current.scenes.toMutableList()
        if (scenes.isNotEmpty()) {
            val scene = scenes[0]
            scenes[0] = scene.copy(overlays = scene.overlays + overlay)
            _blueprint.value = current.copy(scenes = scenes)
        }
    }

    fun startExport(outputPath: String) {
        _exportState.value = ExportState.Exporting(0f)
        viewModelScope.launch {
            try {
                // Delegate to ExportEngine
                _exportState.value = ExportState.Done(outputPath)
            } catch (e: Exception) {
                _exportState.value = ExportState.Failed(e.message ?: "Export failed")
            }
        }
    }
}
