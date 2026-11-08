package com.kinecut.app.ui.audioeditor.engine

class Lame {
    external fun encodeBuffer(pcmBuffer: ByteArray, pcmBufferSize: Int, mp3Buffer: ByteArray): Int
    external fun encodeBufferInterleaved(pcmBuffer: ByteArray, pcmBufferSize: Int, mp3Buffer: ByteArray): Int
    external fun flush(mp3Buffer: ByteArray): Int
    external fun close()
    external fun setInSampleRate(sampleRate: Int)
    external fun setOutChannels(channels: Int)
    external fun setOutBitrate(bitrate: Int)
    external fun setQuality(quality: Int)

    companion object {
        init {
            try {
                System.loadLibrary("kinecut_mp3lame")
            } catch (e: UnsatisfiedLinkError) {
                throw RuntimeException("Failed to load mp3lame library", e)
            }
        }
    }
}
