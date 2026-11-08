package com.kinecut.app.ui.captions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kinecut.app.data.model.WordData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AutoCaptionsViewModel(application: Application) : AndroidViewModel(application) {

    data class EditorSnapshot(
        val words: List<WordData>,
        val style: CaptionStyle,
        val currentTimeMs: Long
    )

    private val _words = MutableStateFlow<List<WordData>>(emptyList())
    val words: StateFlow<List<WordData>> = _words.asStateFlow()

    private val _style = MutableStateFlow(CaptionStyle())
    val style: StateFlow<CaptionStyle> = _style.asStateFlow()

    fun setStyle(style: CaptionStyle) { _style.value = style }
    fun setWords(words: List<WordData>) { _words.value = words }
}
