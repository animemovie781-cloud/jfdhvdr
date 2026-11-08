package com.kinecut.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kinecut.app.ui.landing.LandingScreen
import com.kinecut.app.ui.editor.EditorScreen
import com.kinecut.app.ui.captions.AutoCaptionsScreen
import com.kinecut.app.ui.bgremover.BgRemoverScreen
import com.kinecut.app.ui.audioeditor.AudioEditorScreen

@Composable
fun KineCutApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            LandingScreen(
                onNewProject = { navController.navigate("editor") },
                onOpenCaptions = { navController.navigate("captions") },
                onOpenBgRemover = { navController.navigate("bgremover") },
                onOpenAudioEditor = { navController.navigate("audioeditor") }
            )
        }
        composable("editor") {
            EditorScreen(onBack = { navController.popBackStack() })
        }
        composable("captions") {
            AutoCaptionsScreen(onBack = { navController.popBackStack() })
        }
        composable("bgremover") {
            BgRemoverScreen(onBack = { navController.popBackStack() })
        }
        composable("audioeditor") {
            AudioEditorScreen(onBack = { navController.popBackStack() })
        }
    }
}
