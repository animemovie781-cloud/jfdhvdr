package com.kinecut.app.ui.editor

import android.content.Context
import com.kinecut.app.data.model.Blueprint
import com.kinecut.app.engine.ExportEngine
import com.kinecut.app.export.ExportForegroundService
import java.io.File

class ExportController(private val context: Context) {

    fun startExport(blueprint: Blueprint, outputDir: File) {
        val outputPath = File(outputDir, "kinecut_${System.currentTimeMillis()}.mp4").absolutePath
        ExportForegroundService.start(context, blueprint.id)
    }
}
