package com.kinecut.app.errors

data class ReportedProblem(
    val id: String,
    val message: String,
    val trace: StepTrace? = null,
    val timestamp: Long = System.currentTimeMillis()
)
