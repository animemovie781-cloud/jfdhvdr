package com.kinecut.app.speech.gemini

enum class FailReason(val description: String) {
    NETWORK_ERROR("Network connection failed"),
    INVALID_API_KEY("Invalid API key"),
    RATE_LIMITED("Rate limit exceeded"),
    SERVICE_UNAVAILABLE("Service temporarily unavailable"),
    UNKNOWN("Unknown error")
}
