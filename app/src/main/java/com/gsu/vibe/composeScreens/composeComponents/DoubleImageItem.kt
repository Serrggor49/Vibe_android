package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
fun DoubleImageItem(
    songs: List<SoundModel>,
    navController: NavController
    ) {

    val viewModelStoreOwner = LocalContext.current.findActivity() ?: throw IllegalStateException("Activity not found") // Находим Activity, приводим к ComponentActivity и генерируем исключение, если она null
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner) // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null

    Row(
        Modifier
            .height(IntrinsicSize.Max)
            .background(Color.Transparent)
    ) {

        songs.take(2).forEachIndexed { index, song ->
            Button(
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
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(paddingForCards)
                    .clip(RoundedCornerShape(radiusForCards)),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                ) {
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


//            Button(
//                onClick = { /* TODO */ },
//                contentPadding = PaddingValues(0.dp),
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .padding(paddingForCards)
//                    .clip(RoundedCornerShape(radiusForCards))
//
//            ) {
//                Box {
//                    Image(
//                        painter = painterResource(images[1].preview),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        contentScale = ContentScale.Crop
//                    )
//                    Text(
//                        text = "Текст ${images[0].title + 2}",
//                        modifier = Modifier
//                            .align(Alignment.BottomStart)
//                            .padding(paddingForTextCards)
//                    )
//                }
//            }
    }


}