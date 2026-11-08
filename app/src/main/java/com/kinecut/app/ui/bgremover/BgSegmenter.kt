package com.kinecut.app.ui.bgremover

import android.content.Context
import android.graphics.Bitmap

class BgSegmenter(private val context: Context) {

    class ModelDownloadException(message: String) : Exception(message)
    class NoSubjectException : Exception("No subject detected in the image")

    suspend fun removeBackground(bitmap: Bitmap): Bitmap {
        return bitmap
    }

    suspend fun ensureModelDownloaded() {
    }
}
