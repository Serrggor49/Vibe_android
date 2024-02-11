package com.gsu.vibe.composeScreens.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gsu.vibe.presentation.MainViewModel


@Composable
fun MeditationScreen(navController: NavController) {

    val mainViewModel: MainViewModel = viewModel()
    val sounds = mainViewModel.getListForMeditaionForCompose()

    var size by remember { mutableStateOf(Size.Zero) } // Инициализация размера с нулевым значением
    val gradient = Brush.linearGradient(
        listOf(Color(0xFF2A3572), Color(0xFF0B1130), Color(0xFF2A3572)),
        start = Offset.Zero, // Начало в левом верхнем углу
        end = Offset(size.width, size.height) // Конец в правом нижнем углу
    )

    Box(
        modifier = Modifier
            .background(gradient)
            .onGloballyPositioned { coordinates ->
                size = coordinates.size.toSize() // Получение и сохранение текущего размера
            }
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        MosaicColumn(
            list = sounds,
            navController = navController
        )
    }
}
