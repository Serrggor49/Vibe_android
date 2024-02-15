package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
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


/**
 * Cперва рисуется левый столбец. Для картинки строятся максимально по ширине (пол экрана) затем
 * высчитывается высота, примерно как adjustViewBounds, чтобы по высоте изображение сохраняло
 * пропорции относительно ширины. Затем рисуется второй столбец. Ширина изобржаний все также равна
 * половине ширины экрана, а пропорции повторяют значения левого столца. Разница  только в том, что
 * они меняются местами (нижний с верхним). Поэтому нужно использовать похожие по соотношению сторон
 * изображения.
 */

@Composable
fun QuadroImageItem(
        songs: List<SoundModel>,
        navController: NavController
) {

    val viewModelStoreOwner = LocalContext.current.findActivity() ?: throw IllegalStateException("Activity not found") // Находим Activity, приводим к ComponentActivity и генерируем исключение, если она null
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner) // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null

    var ratio1 =
        (painterResource(songs[0].preview).intrinsicSize.width) / (painterResource(songs[0].preview).intrinsicSize.height)
    var ratio2 =
        (painterResource(songs[1].preview).intrinsicSize.width) / (painterResource(songs[1].preview).intrinsicSize.height)

    Row(
        Modifier.background(Color.Transparent)
    ) {
        Column(
            Modifier
                .weight(1f)
            // .fillMaxHeight()
        ) {
            songs.take(2).forEachIndexed { index, song ->
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
                        .padding(paddingForCards)
                        .clip(RoundedCornerShape(radiusForCards))

                ) {
                    Box {
                        val currentRatio = if (index == 0) ratio1 else ratio2
                        Image(
                            painter = painterResource(song.preview),
                            contentDescription = "",
                            modifier = Modifier.aspectRatio(currentRatio),
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

        Column(
            Modifier
                .weight(1f)
            //  .fillMaxHeight()
        ) {

            songs.drop(2).forEachIndexed{ index, song ->
                Button(
                    modifier = Modifier
                        .padding(paddingForCards)
                        .clip(RoundedCornerShape(radiusForCards)),
                    onClick = {
                        viewModel.setCurrentSound(name = songs[index+2].name)
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
                ) {
                    Box {
                        Image(
                            modifier = if(index == 0) Modifier.aspectRatio(ratio2) else Modifier.aspectRatio(ratio1),
                            painter = painterResource(song.preview),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(paddingForTextCards),
                            text = stringResource(id = song.title)
                        )
                    }
                }
            }

        }

    }
}