package com.gsu.vibe.composeScreens.player.playerComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SliderForPlayer() {
    Slider(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        value = 50f,
        onValueChange = { newValue ->
            //  sliderPosition = newValue
        },
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray),
            valueRange = 0f..100f,  // Диапазон значений для Slider
            onValueChangeFinished = {
                // Вызывается, когда пользователь заканчивает перемещение слайдера
            },
        )
}
