package com.gsu.vibe.composeScreens.composeComponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.screens.findActivity
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards

@Composable
fun TripleImageItem(songs: List<SoundModel>, leftSideSingleImage: Boolean = false, navController: NavController) {

    val viewModelStoreOwner = LocalContext.current.findActivity() ?: throw IllegalStateException("Activity not found") // Находим Activity, приводим к ComponentActivity и генерируем исключение, если она null
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner) // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null


    Row {
        val modifierForSingleButton = Modifier
            .weight(1f)
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))

        var buttonHeight1 = 0.dp

        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            val maxWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }

            Button(
                modifier = modifierForSingleButton.height(maxWidth * 2),
                onClick = {
                    viewModel.setCurrentSound(name = songs[0].name)
                    navController.navigate("mediaPlayerComposeScreen") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true // Избегаем повторной загрузки экрана, если он уже загружен
                        restoreState = true // Восстановление состояния при переходе назад к экрану
                    }
                },
                contentPadding = PaddingValues(0.dp)
            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentDescription = "",
                        painter = painterResource(songs[0].preview),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(paddingForTextCards),
                        text = stringResource(id = songs[0].title)
                    )
                }
            }

            buttonHeight1 = with(LocalDensity.current) { constraints.maxHeight.toDp() }
            buttonHeight1 = with(LocalDensity.current) { constraints.maxHeight.toDp() }
            Log.d("MyLogs112", "maxHeight = ${buttonHeight1}")

        }

        BoxWithConstraints(
            Modifier
                .weight(1f)
        ) {
            val maxWidth =
                with(LocalDensity.current) { constraints.maxWidth.toDp() + paddingForCards }
            Log.d("MyLogs112", "maxWidth = ${maxWidth}")

            Column {
                for ((index,song) in songs.withIndex().drop(1)) {
                    Button(
                        onClick = {
                            viewModel.setCurrentSound(name = songs[index].name)
                            navController.navigate("mediaPlayerComposeScreen") {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true // Избегаем повторной загрузки экрана, если он уже загружен
                                restoreState = true // Восстановление состояния при переходе назад к экрану
                            }
                        },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .height(maxWidth)
                            .padding(paddingForCards)
                            .clip(RoundedCornerShape(radiusForCards))
                    ) {
                        Box {
                            Image(
                                painter = painterResource(song.preview),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = stringResource(id = song.title),
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(paddingForTextCards)
                            )
                        }
                    }
                }
            }

        }
    }
}
