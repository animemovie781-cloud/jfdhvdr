package com.kinecut.app.viraleffects.core

import android.media.MediaCodec
import android.media.MediaMuxer

abstract class VfxVideoWriter(outputPath: String) {

    class EncoderException(message: String) : Exception(message)
    class PictureException(message: String) : Exception(message)

    protected val muxer = MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)

    abstract fun addFrame(rgbFrame: IntArray, width: Int, height: Int, timeMs: Long)
    abstract fun release()
}
