package com.kinecut.app.data.repository

import android.content.Context
import com.kinecut.app.data.model.ProjectMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ProjectRepository(private val context: Context) {

    private val projectsDir: File
        get() = File(context.filesDir, "projects").also { it.mkdirs() }

    suspend fun loadAll(): List<ProjectMeta> = withContext(Dispatchers.IO) {
        projectsDir.listFiles()?.mapNotNull { dir ->
            try {
                val meta = File(dir, "meta.json")
                if (meta.exists()) {
                    com.google.gson.Gson().fromJson(meta.readText(), ProjectMeta::class.java)
                } else null
            } catch (e: Exception) {
                null
            }
        }?.sortedByDescending { it.lastModified } ?: emptyList()
    }

    suspend fun save(meta: ProjectMeta) = withContext(Dispatchers.IO) {
        val dir = File(projectsDir, meta.id).also { it.mkdirs() }
        File(dir, "meta.json").writeText(
            com.google.gson.Gson().toJson(meta)
        )
    }

    suspend fun delete(id: String) = withContext(Dispatchers.IO) {
        File(projectsDir, id).deleteRecursively()
    }
}
