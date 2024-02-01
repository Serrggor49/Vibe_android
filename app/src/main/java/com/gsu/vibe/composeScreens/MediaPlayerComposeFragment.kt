package com.gsu.vibe.composeScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.flaviofaria.kenburnsview.KenBurnsView
import com.gsu.vibe.R
import com.gsu.vibe.composeScreens.composeComponents.playerComponents.PlayerWindow
import com.gsu.vibe.composeScreens.composeComponents.playerComponents.SetTimerComponent
import kotlin.random.Random

class MediaPlayerComposeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyScreen()
            }
        }
    }

    @Composable
    fun MyScreen() {
        KenBurnsEffectFullScreen(modifier = Modifier.fillMaxSize(), imageRes = R.drawable.focus_04_1b)
        Box(modifier = Modifier
            .fillMaxSize()
            ) {

            SetTimerComponent()
            PlayerWindow()

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





}