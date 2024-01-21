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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards

@Composable
fun TripleImageItem(songs: List<SoundModel>, leftSideSingleImage: Boolean = false) {

    val heightItem = 0.dp

    Row {
        val modifierForColumnWithTwoButtons = Modifier.weight(1f)
        val modifierForSingleButton = Modifier
            .weight(1f)
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))


        var buttonHeight1 = 0.dp

        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            val maxWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }

            Button(
                onClick = { },
                contentPadding = PaddingValues(0.dp),
                modifier = modifierForSingleButton.height(maxWidth * 2)
            ) {
                Box {
                    Image(
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize(),
                        //.aspectRatio(painterResource(songs[0].preview).intrinsicSize.width / painterResource(songs[0].preview).intrinsicSize.height), // Сохранить пропорции
                        painter = painterResource(songs[0].preview),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Текст 1",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(paddingForTextCards)
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
            val maxWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() + paddingForCards}
            Log.d("MyLogs112", "maxWidth = ${maxWidth}")

            Column {
                for (song in songs.drop(1)) {
                    Button(
                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            //.weight(1f)
                            // .fillMaxHeight()
                            //
                            //.height(buttonHeight)
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
                                // .height(buttonHeight),
                                // .wrapContentHeight(),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Текст ${2}",
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
