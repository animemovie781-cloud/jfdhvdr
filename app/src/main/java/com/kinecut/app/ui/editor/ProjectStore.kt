package com.kinecut.app.ui.editor

import android.content.Context
import com.kinecut.app.data.model.Blueprint
import com.kinecut.app.data.model.BlueprintConfig
import com.google.gson.Gson
import java.io.File
import java.util.UUID

class ProjectStore(private val context: Context) {

    private val gson = Gson()
    private val projectsDir: File
        get() = File(context.filesDir, "projects").also { it.mkdirs() }

    fun createNew(name: String = "Untitled"): Blueprint {
        return Blueprint(
            id = UUID.randomUUID().toString(),
            name = name,
            config = BlueprintConfig()
        )
    }

    fun save(blueprint: Blueprint) {
        val file = File(projectsDir, "${blueprint.id}.json")
        file.writeText(gson.toJson(blueprint))
    }

    fun load(id: String): Blueprint? {
        val file = File(projectsDir, "$id.json")
        return if (file.exists()) {
            gson.fromJson(file.readText(), Blueprint::class.java)
        } else null
    }
}
