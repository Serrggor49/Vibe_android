package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.color.utilities.MaterialDynamicColors.background
import com.gsu.vibe.data.models.SoundModel

import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards


@Composable
fun DoubleImageItem(images: List<SoundModel>) {
    Row(
        Modifier
            .height(IntrinsicSize.Max)
            .background(Color.Transparent)
    ) {
        Column(
            Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            images.take(1).forEachIndexed { index, song ->
                Button(
                    onClick = { /* TODO */ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(radiusForCards))
                        .padding(paddingForCards),
                    ) {
                    Box(modifier = Modifier.background(Color.Transparent)) {
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

        Column(
            Modifier
                .weight(1f)
                .wrapContentHeight() // Заполнение максимальной высоты
        ) {
            images.drop(1).forEachIndexed { index, song  ->
                Button(
                    onClick = { /* TODO */ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingForCards)
                        .clip(RoundedCornerShape(radiusForCards))
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
}