package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
fun QuadroImageItem(images: List<SoundModel>) {

    var ratio1 =
        (painterResource(images[0].preview).intrinsicSize.width) / (painterResource(images[0].preview).intrinsicSize.height)
    var ratio2 =
        (painterResource(images[1].preview).intrinsicSize.width) / (painterResource(images[1].preview).intrinsicSize.height)

    Row(
        Modifier.background(Color.Transparent)
    ) {
        Column(
            Modifier
                .weight(1f)
               // .fillMaxHeight()
        ) {
            images.take(2).forEachIndexed { index, song ->
                Button(
                    onClick = { /* TODO */ },
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
              //  .fillMaxHeight()
        ) {
            Button(
                onClick = { /* TODO */ },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(paddingForCards)
                    .clip(RoundedCornerShape(radiusForCards))
            ) {
                Box {
                    Image(
                        painter = painterResource(images[2].preview),
                        contentDescription = "",
                        modifier = Modifier.aspectRatio(ratio2),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "${images[2].title}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(paddingForTextCards)
                    )
                }
            }

            Button(
                onClick = { /* TODO */ },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(paddingForCards)
                    .clip(RoundedCornerShape(radiusForCards))
            ) {
                Box {
                    Image(
                        painter = painterResource(images[3].preview),
                        contentDescription = "",
                        modifier = Modifier.aspectRatio(ratio1),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "${images[2].title}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(paddingForTextCards)
                    )
                }
            }
        }

    }
}