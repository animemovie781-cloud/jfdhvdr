package com.kinecut.app.scriptvoice.gemini

data class FailVerdict(
    val reason: String,
    val retryable: Boolean = true
)
