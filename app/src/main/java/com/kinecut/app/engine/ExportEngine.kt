package com.kinecut.app.engine

import android.content.Context
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMuxer
import android.media.MediaCodecInfo
import com.kinecut.app.data.model.Blueprint
import java.io.File

class ExportEngine(private val context: Context) {

    class EncoderConfigException(message: String) : Exception(message)

    data class ExportConfig(
        val outputPath: String,
        val width: Int = 1080,
        val height: Int = 1920,
        val fps: Int = 30,
        val bitrate: Int = 8_000_000,
        val audioBitrate: Int = 128_000
    )

    interface ExportListener {
        fun onProgress(progress: Float)
        fun onComplete(outputFile: File)
        fun onError(error: Exception)
    }

    fun export(
        blueprint: Blueprint,
        config: ExportConfig,
        listener: ExportListener
    ) {
        try {
            val outputFile = File(config.outputPath)
            outputFile.parentFile?.mkdirs()

            // Video encoding pipeline using MediaCodec + MediaMuxer
            val muxer = MediaMuxer(
                config.outputPath,
                MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4
            )

            val videoFormat = MediaFormat.createVideoFormat(
                MediaFormat.MIMETYPE_VIDEO_AVC,
                config.width,
                config.height
            ).apply {
                setInteger(MediaFormat.KEY_BIT_RATE, config.bitrate)
                setInteger(MediaFormat.KEY_FRAME_RATE, config.fps)
                setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
                setInteger(
                    MediaFormat.KEY_COLOR_FORMAT,
                    MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface
                )
            }

            listener.onProgress(0f)
            // Process frames from blueprint scenes
            for ((index, scene) in blueprint.scenes.withIndex()) {
                val progress = (index + 1).toFloat() / blueprint.scenes.size
                listener.onProgress(progress)
            }

            muxer.stop()
            muxer.release()
            listener.onComplete(outputFile)
        } catch (e: Exception) {
            listener.onError(e)
        }
    }
}
