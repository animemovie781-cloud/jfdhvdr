package com.kinecut.app.errors

data class StepTrace(
    val steps: List<String> = emptyList()
) {
    fun append(step: String): StepTrace = copy(steps = steps + step)
}
