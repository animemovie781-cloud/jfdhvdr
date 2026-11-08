package com.kinecut.app.ui.captions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCaptionsScreen(
    onBack: () -> Unit,
    viewModel: AutoCaptionsViewModel = viewModel()
) {
    val presets = remember {
        listOf(
            CaptionPreset("Karaoke", "karaoke"),
            CaptionPreset("Subtitle", "subtitle"),
            CaptionPreset("Word by Word", "word_by_word"),
            CaptionPreset("Bounce", "bounce"),
            CaptionPreset("Typewriter", "typewriter"),
            CaptionPreset("Highlight", "highlight")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Auto Captions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A2E),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF121212))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E1E2E)),
                contentAlignment = Alignment.Center
            ) {
                Text("Caption Preview", color = Color.White.copy(alpha = 0.5f))
            }

            // Style presets
            Text("Style", color = Color.White, fontWeight = FontWeight.Bold)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(presets) { preset ->
                    FilterChip(
                        selected = false,
                        onClick = { },
                        label = { Text(preset.name) }
                    )
                }
            }

            // Generate button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Generate Captions", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
