package com.kinecut.app.scriptvoice.voice

class SupertonicVoice {
    interface ChunkCallback {
        fun onChunk(audioBytes: ByteArray, offsetMs: Long)
        fun onComplete()
        fun onError(error: Exception)
    }
}
