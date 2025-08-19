package com.example.fiti_teep.ui.components

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.fiti_teep.R

@OptIn(UnstableApi::class)
@Composable
fun VideoLoader(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Keep ExoPlayer instance remembered across recompositions
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(
                "android.resource://${context.packageName}/${R.raw.pawpuls_loader}"
            )
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ALL // loop forever
        }
    }

    // Release when composable leaves
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(), // ✅ Take full screen
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                useController = false // hide controls
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                // ✅ fills screen nicely without black bars
            }
        }
    )
}
