package com.kinecut.app.viraleffects.flightmap.video

import com.kinecut.app.viraleffects.core.VfxVideoWriter

class MapVideoWriter(outputPath: String) : VfxVideoWriter(outputPath) {

    class EncoderException(message: String) : Exception(message)
    class PictureException(message: String) : Exception(message)

    override fun addFrame(rgbFrame: IntArray, width: Int, height: Int, timeMs: Long) {
        // Render map overlay onto frame
    }

    override fun release() {
        muxer.stop()
        muxer.release()
    }
}
