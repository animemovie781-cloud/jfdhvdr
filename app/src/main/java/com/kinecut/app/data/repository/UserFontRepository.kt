package com.kinecut.app.data.repository

import android.content.Context
import com.kinecut.app.fontlibrary.LibraryFont
import java.io.File

class UserFontRepository(private val context: Context) {

    private val fontsDir: File
        get() = File(context.filesDir, "user_fonts").also { it.mkdirs() }

    fun getInstalledFonts(): List<LibraryFont> {
        return fontsDir.listFiles()?.filter {
            it.extension in listOf("ttf", "otf")
        }?.map {
            LibraryFont(it.nameWithoutExtension, it.absolutePath)
        } ?: emptyList()
    }

    fun importFont(sourceFile: File): LibraryFont {
        val dest = File(fontsDir, sourceFile.name)
        sourceFile.copyTo(dest, overwrite = true)
        return LibraryFont(dest.nameWithoutExtension, dest.absolutePath)
    }
}
