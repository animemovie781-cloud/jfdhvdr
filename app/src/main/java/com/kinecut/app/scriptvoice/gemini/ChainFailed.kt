package com.kinecut.app.scriptvoice.gemini

class ChainFailed(val step: String, message: String) : Exception(message)
