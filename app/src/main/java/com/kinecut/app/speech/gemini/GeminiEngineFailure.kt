package com.kinecut.app.speech.gemini

class GeminiEngineFailure(val failReason: FailReason) : Exception(failReason.description)
