package com.gsu.vibe.composeScreens.composeComponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.screens.findActivity
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards

@Composable
fun SingleImageItem(
    songs: List<SoundModel>,
    navController: NavController,
) {

  //  val viewModel: MediaPlayerComposeViewModel = viewModel()

    val context = LocalContext.current
    // Находим Activity, приводим к ComponentActivity и генерируем исключение, если она null
    val viewModelStoreOwner = context.findActivity() ?: throw IllegalStateException("Activity not found")
    // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null
    val viewModel: MediaPlayerComposeViewModel = viewModel(viewModelStoreOwner)


    Button(
        onClick = {
            viewModel.setCurrentSound(name = songs[0].name)
            viewModel.testString = "1"
            Log.d("MyLogs33", "songNameSingle = ${songs[0].name}")
//            Log.d("MyLogs33", "navc1 = ${navController.hashCode()}")
            Log.d("MyLogs33", "navc1 = ${viewModel.hashCode()}")
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
            .clip(RoundedCornerShape(radiusForCards))
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))
    )
    {
        Box {
            Image(
                painter = painterResource(id = songs[0].preview),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(paddingForTextCards),
                text = stringResource(id = songs[0].title),
                fontSize = 18.sp
            )
        }
    }
}

