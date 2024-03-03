package com.gsu.vibe.composeScreens.player.playerComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.screens.findActivity

@Composable
fun SliderForPlayer() {

    val viewModelStoreOwner = LocalContext.current.findActivity()!!
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner)

    Slider(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        value = 0f,
        onValueChange = { newValue ->
            //  sliderPosition = newValue
        },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray),
            //valueRange = 0f..viewModel.state.value.durationInMs.toFloat(),  // Диапазон значений для Slider
            valueRange = 0f..100f,  // Диапазон значений для Slider
            onValueChangeFinished = {
                // Вызывается, когда пользователь заканчивает перемещение слайдера
            },

        )
}
