package com.gsu.vibe.composeScreens.screens

import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.flaviofaria.kenburnsview.KenBurnsView
import com.gsu.vibe.R
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.player.playerComponents.PlayerWindow
import com.gsu.vibe.composeScreens.player.playerComponents.SetTimerComponent

@Composable
fun MediaPlayerComposeScreen(navController: NavController) {

    val viewModel: MediaPlayerComposeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val state = viewModel.state.collectAsState()
    Log.d("MyLogs554", "collectAsState")

    KenBurnsEffectFullScreen(modifier = Modifier.fillMaxSize(), imageRes = R.drawable.focus_04_1b)
    Box(modifier = Modifier.fillMaxSize()) {
        PlayerWindow(state.value.name)
        SetTimerComponent()
    }
}

val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
val generator = com.gsu.vibe.services.RandomTransitionGenerator(25000, ACCELERATE_DECELERATE)

@Composable
fun KenBurnsEffectFullScreen(modifier: Modifier = Modifier, imageRes: Int) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            KenBurnsView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(imageRes)
                setTransitionGenerator(generator)
            }
        },
        update = {
            // В случае необходимости обновления изображения
            it.setImageResource(imageRes)
        }
    )
}