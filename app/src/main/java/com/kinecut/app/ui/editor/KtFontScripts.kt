package com.kinecut.app.ui.editor

object KtFontScripts {
    enum class Script(val displayName: String, val fontAsset: String?) {
        LATIN("Latin", null),
        DEVANAGARI("Devanagari", "fonts/lang/HindVadodara-Regular.ttf"),
        BENGALI("Bengali", "fonts/lang/BalooDa2-Regular.ttf"),
        GURMUKHI("Gurmukhi", "fonts/lang/BalooPaaji2-Regular.ttf"),
        TELUGU("Telugu", "fonts/lang/Ramabhadra-Regular.ttf"),
        ARABIC("Arabic", "fonts/lang/Lateef-Regular.ttf");

        companion object {
            fun detect(text: String): Script {
                for (char in text) {
                    return when {
                        char.code in 0x0900..0x097F -> DEVANAGARI
                        char.code in 0x0980..0x09FF -> BENGALI
                        char.code in 0x0A00..0x0A7F -> GURMUKHI
                        char.code in 0x0C00..0x0C7F -> TELUGU
                        char.code in 0x0600..0x06FF -> ARABIC
                        else -> continue
                    }
                }
                return LATIN
            }
        }
    }
}
