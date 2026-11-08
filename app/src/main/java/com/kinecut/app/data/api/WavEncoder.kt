package com.kinecut.app.data.api

import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class WavEncoder(
    private val sampleRate: Int = 16000,
    private val channels: Int = 1,
    private val bitsPerSample: Int = 16
) {
    fun writeHeader(out: OutputStream, dataSize: Int) {
        val buffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN)
        val byteRate = sampleRate * channels * bitsPerSample / 8
        val blockAlign = channels * bitsPerSample / 8

        buffer.put("RIFF".toByteArray())
        buffer.putInt(36 + dataSize)
        buffer.put("WAVE".toByteArray())
        buffer.put("fmt ".toByteArray())
        buffer.putInt(16)
        buffer.putShort(1)
        buffer.putShort(channels.toShort())
        buffer.putInt(sampleRate)
        buffer.putInt(byteRate)
        buffer.putShort(blockAlign.toShort())
        buffer.putShort(bitsPerSample.toShort())
        buffer.put("data".toByteArray())
        buffer.putInt(dataSize)

        out.write(buffer.array())
    }
}
