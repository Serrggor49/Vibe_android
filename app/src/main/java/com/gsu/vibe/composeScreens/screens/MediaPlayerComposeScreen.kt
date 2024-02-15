package com.gsu.vibe.composeScreens.screens

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.flaviofaria.kenburnsview.KenBurnsView
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.player.playerComponents.PlayerWindow
import com.gsu.vibe.composeScreens.player.playerComponents.SetTimerComponent

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun MediaPlayerComposeScreen(navController: NavController) {

    //val viewModel : MediaPlayerComposeViewModel = viewModel()

    val context = LocalContext.current
    // Находим Activity, приводим к ComponentActivity и генерируем исключение, если она null
    val viewModelStoreOwner = context.findActivity() ?: throw IllegalStateException("Activity not found")
    // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner)

    val state = viewModel.state.collectAsState()

    KenBurnsEffectFullScreen(modifier = Modifier.fillMaxSize(), imageRes = state.value.background)
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