package com.kinecut.app.fontlibrary

data class LibraryFont(
    val name: String,
    val path: String,
    val isAsset: Boolean = false,
    val weight: Int = 400
)
