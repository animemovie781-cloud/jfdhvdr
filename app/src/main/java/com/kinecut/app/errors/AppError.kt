package com.kinecut.app.errors

sealed class AppError(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class TranscriptionFailed(message: String, cause: Throwable? = null) : AppError(message, cause)
    class ExportFailed(message: String, cause: Throwable? = null) : AppError(message, cause)
    class NoAudioTrack(message: String = "No audio track found") : AppError(message)
    class InvalidProject(message: String) : AppError(message)
    class NetworkError(message: String, cause: Throwable? = null) : AppError(message, cause)
}
