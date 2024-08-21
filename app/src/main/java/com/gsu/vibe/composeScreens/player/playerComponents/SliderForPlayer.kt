package com.gsu.vibe.composeScreens.player.playerComponents

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var sliderValue by remember { mutableStateOf(0f) }
    val state = viewModel.state.collectAsState()

    Slider(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        //value = sliderValue,
        onValueChange = { newValue ->
            sliderValue = newValue

        },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray
        ),
        valueRange = 0f..viewModel.state.collectAsState().value.durationInMs.toFloat(), // Диапазон значений для Slider

        value = state.value.currentTrackTime.toFloat(),

        onValueChangeFinished = { // Вызывается, когда пользователь заканчивает перемещение слайдера
            Log.d("MyLogs88", "slider value = ${sliderValue}")
            viewModel.setTime(sliderValue.toLong())
//            _state.value = _state.value.copy(isPlaying = false)

        },

        )
}
