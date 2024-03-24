package com.gsu.vibe.composeScreens.screens

import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.flaviofaria.kenburnsview.KenBurnsView
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.player.playerComponents.PlayerWindow
import com.gsu.vibe.composeScreens.player.playerComponents.SetTimerComponent
import com.gsu.vibe.composeServices.PlayerService

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private lateinit var musicService: PlayerService
private var isBound = false

@Composable
fun MediaPlayerComposeScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModelStoreOwner = context.findActivity()!!
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner)
    val state = viewModel.state.collectAsState()

    KenBurnsEffectFullScreen(modifier = Modifier.fillMaxSize(), imageRes = state.value.background)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            painter = painterResource(id = viewModel.state.value.foreground),
            contentDescription = "",
        )

        PlayerWindow()

        if (!state.value.isDownloaded) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(0.4f)
            )
        }
        SetTimerComponent()


//        val connection = object : ServiceConnection {
//            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                val binder = service as PlayerService.PlayerBinder
//                musicService = binder.getServise()
//                isBound = true
//                musicService.play()
//                //        }
//
//            }
//
//            override fun onServiceDisconnected(name: ComponentName?) {
//                isBound = false
//            }
//
//        }

        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as PlayerService.PlayerBinder
                val musicService = binder.getService()
                Log.d("MyLogs88", "url = ${state.value.url}")
                musicService.setTrackUri("/data/data/com.gsu.vibe/files/${state.value.name}")
                musicService.play()
            }

            override fun onServiceDisconnected(name: ComponentName?) {}
        }, Context.BIND_AUTO_CREATE)

    }
}

@Composable
fun KenBurnsEffectFullScreen(modifier: Modifier = Modifier, imageRes: Int) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            KenBurnsView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(imageRes)
                setTransitionGenerator(
                    RandomTransitionGenerator(
                        25000,
                        AccelerateDecelerateInterpolator()
                    )
                )
            }
        },
        update = {
            // В случае необходимости обновления изображения
            it.setImageResource(imageRes)
        }
    )
}