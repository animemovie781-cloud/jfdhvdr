package com.kinecut.app.keyfree

class NoAiKeyException(service: String) :
    Exception("No API key configured for $service. Please add your key in Settings.")
