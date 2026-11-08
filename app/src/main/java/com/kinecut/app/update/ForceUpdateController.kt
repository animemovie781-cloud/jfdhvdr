package com.kinecut.app.update

import android.content.Context

class ForceUpdateController(private val context: Context) {

    fun checkForUpdate(
        currentVersion: String,
        onUpdateRequired: (String) -> Unit
    ) {
        // Check remote config for force update version
    }
}
