package com.kinecut.app.scriptvoice.gemini

class GeminiHttpError(val code: Int, message: String) : Exception("HTTP $code: $message")
