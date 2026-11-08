package com.kinecut.app.viraleffects.countrymap.video

import com.kinecut.app.viraleffects.core.VfxVideoWriter

class CmapVideoWriter(outputPath: String) : VfxVideoWriter(outputPath) {

    class EncoderException(message: String) : Exception(message)
    class PictureException(message: String) : Exception(message)

    override fun addFrame(rgbFrame: IntArray, width: Int, height: Int, timeMs: Long) {
        // Render country map overlay onto frame
    }

    override fun release() {
        muxer.stop()
        muxer.release()
    }
}
