package com.kinecut.app.data.api

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

suspend fun <T> stoppable(block: suspend () -> T): T {
    currentCoroutineContext().ensureActive()
    return try {
        block()
    } catch (e: CancellationException) {
        throw e
    }
}
