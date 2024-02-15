package com.gsu.vibe.composeScreens.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.gsu.vibe.composeScreens.Screens
import com.gsu.vibe.composeScreens.composeComponents.DoubleImageItem
import com.gsu.vibe.composeScreens.composeComponents.InitHeaderBlock
import com.gsu.vibe.composeScreens.composeComponents.QuadroImageItem
import com.gsu.vibe.composeScreens.composeComponents.SingleImageItem
import com.gsu.vibe.composeScreens.composeComponents.TripleImageItem
import com.gsu.vibe.data.models.ItemType
import com.gsu.vibe.data.models.SongsBlock
import com.gsu.vibe.presentation.MainViewModel

@Composable
fun SleepScreen(navController: NavController) {

    val mainViewModel: MainViewModel = viewModel()
    val sounds = mainViewModel.getListForSleepForCompose()

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

var leftSideSingleImage = false


@Composable
fun MosaicColumn(list: List<SongsBlock>, navController: NavController) {
    LazyColumn {
        item { InitHeaderBlock() }
        items(list) { item1 ->
            when (item1.type) {
                ItemType.Single -> SingleImageItem(songs =  item1.songs, navController = navController)
                ItemType.Triple -> {
                    TripleImageItem(
                        item1.songs,
                        leftSideSingleImage = leftSideSingleImage,
                        navController = navController
                    )
                    leftSideSingleImage = !leftSideSingleImage
                }
                ItemType.Double -> DoubleImageItem(item1.songs, navController)
                ItemType.Quadruple -> QuadroImageItem(item1.songs, navController)
            }
        }

        item { Spacer(modifier = Modifier.padding(bottom = 120.dp)) }
    }
}

fun onImageClick(songName: String){
    Log.d("MyLogs33", "songName = $songName")

//        val action = SleepComposeFragmentDirections.actionSleepComposeFragmentToMediaPlayerComposeFragment()
//        view?.findNavController()?.navigate(action)
}