package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards

@Composable
fun TripleImageItem(songs: List<SoundModel>, leftSideSingleImage: Boolean = false) {
    Row(
        Modifier.height(IntrinsicSize.Max)
    ) {

        val modifierForColumnWithTwoButtons = Modifier
            .weight(1f)
            .wrapContentHeight()
        val modifierForSingleButton = Modifier
            .weight(1f)
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))
            .fillMaxHeight()
            .background(Color.White)

        if (leftSideSingleImage) {
            GetColumnWithTwoButtons(modifierForColumnWithTwoButtons, songs)
            GetSingleButton(modifierForSingleButton, songs)
        }
        else
        {
            GetSingleButton(modifierForSingleButton, songs)
            GetColumnWithTwoButtons(modifierForColumnWithTwoButtons, songs)
        }
    }
}

@Composable
fun GetColumnWithTwoButtons(modifier: Modifier, songs: List<SoundModel>) {

    Column(modifier = modifier) {
        songs.drop(1).forEachIndexed { index, song ->
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(paddingForCards)
                    .clip(RoundedCornerShape(radiusForCards))
                    .fillMaxWidth()
            ) {
                Box {
                    Image(
                        painter = painterResource(song.preview),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Текст ${index + 2}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(paddingForTextCards)
                    )
                }
            }
        }
    }
}

@Composable
fun GetSingleButton(modifier: Modifier, songs: List<SoundModel>) {
    Button(
        onClick = { /* TODO */ },
        contentPadding = PaddingValues(0.dp),
        modifier = modifier

    ) {
        Box {
            Image(
                painter = painterResource(songs[0].preview),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
}