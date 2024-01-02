package com.gsu.vibe.composeComponents

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
import androidx.compose.ui.unit.dp
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.radiusForCards

@Composable
fun TripleImageItem(images: List<Painter>, leftSideSingleImage: Boolean = false) {
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
            getColumnWithTwoButtons(modifierForColumnWithTwoButtons, images)
            getSingleButton(modifierForSingleButton, images)
        }
        else
        {
            getSingleButton(modifierForSingleButton, images)
            getColumnWithTwoButtons(modifierForColumnWithTwoButtons, images)
        }


//        Column(
//            Modifier
//                .weight(1f)
//                .wrapContentHeight() // Заполнение максимальной высоты
//        ) {
//            images.drop(1).forEachIndexed { index, image ->
//                Button(
//                    onClick = { /* TODO */ },
//                    contentPadding = PaddingValues(0.dp),
//                    modifier = Modifier
//                        .padding(paddingForCards)
//                        .clip(RoundedCornerShape(radiusForCards))
//                        .fillMaxWidth()
//                ) {
//                    Box {
//                        Image(
//                            painter = image,
//                            contentDescription = "",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight(),
//                            contentScale = ContentScale.Crop
//                        )
//                        Text(
//                            text = "Текст ${index + 2}",
//                            modifier = Modifier
//                                .align(Alignment.BottomStart)
//                                .padding(horizontal = 15.dp, vertical = 15.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }

    }


//    Column(
//        Modifier
//            .weight(1f)
//            .wrapContentHeight() // Заполнение максимальной высоты
//    ) {
//        images.drop(1).forEachIndexed { index, image ->
//            Button(
//                onClick = { /* TODO */ },
//                contentPadding = PaddingValues(0.dp),
//                modifier = Modifier
//                    .padding(paddingForCards)
//                    .clip(RoundedCornerShape(radiusForCards))
//                    .fillMaxWidth()
//            ) {
//                Box {
//                    Image(
//                        painter = image,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        contentScale = ContentScale.Crop
//                    )
//                    Text(
//                        text = "Текст ${index + 2}",
//                        modifier = Modifier
//                            .align(Alignment.BottomStart)
//                            .padding(horizontal = 15.dp, vertical = 15.dp)
//                    )
//                }
//            }
//        }
//    }
}


@Composable
fun getColumnWithTwoButtons(modifier: Modifier, images: List<Painter>) {

    Column(modifier = modifier) {
        images.drop(1).forEachIndexed { index, image ->
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
                        painter = image,
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
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                    )
                }
            }
        }
    }

}


@Composable
fun getSingleButton(modifier: Modifier, images: List<Painter>) {
    Button(
        onClick = { /* TODO */ },
        contentPadding = PaddingValues(0.dp),
        modifier = modifier

    ) {
        Box {
            Image(
                painter = images[0],
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
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            )
        }
    }
}