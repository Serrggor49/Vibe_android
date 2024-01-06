package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards


@Composable
fun QuadroImageItem(images: List<SoundModel>) {

    var size1 = 0f
    var size2 = 0f

//    Column {
//        Button(onClick = { /*TODO*/ }) {
//            Box {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        //.fillMaxHeight(),
//                        .aspectRatio(
//                            (painterResource(images[0].preview).intrinsicSize.width) / (painterResource(
//                                images[0].preview
//                            ).intrinsicSize.height)
//                        ), // Сохранить пропорции
//
//                    painter = painterResource(images[0].preview),
//                    contentDescription = ""
//                )
//            }
//        }
//        Button(onClick = { /*TODO*/ }) {
//            Box {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        //.fillMaxHeight(),
//                        .aspectRatio(
//                            (painterResource(images[1].preview).intrinsicSize.width) / (painterResource(
//                                images[1].preview
//                            ).intrinsicSize.height)
//                        ), // Сохранить пропорции
//
//                    painter = painterResource(images[1].preview),
//                    contentDescription = ""
//                )
//            }
//        }
//    }

    size1 =  (painterResource(images[0].preview).intrinsicSize.width) / (painterResource(images[0].preview).intrinsicSize.height)
    size2 =  (painterResource(images[1].preview).intrinsicSize.width) / (painterResource(images[1].preview).intrinsicSize.height)

    Row(
        Modifier
            //.background(Color.Transparent)
            .background(Color.Blue)
    ) {
        Column(
            Modifier.weight(1f)
                .fillMaxHeight()
        ) {
            images.take(2).forEachIndexed { index, song ->
                Button(
                    onClick = { /* TODO */ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(radiusForCards))
                        .padding(paddingForCards)
                    //   .height(IntrinsicSize.Max),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                        //   .height(IntrinsicSize.Max),
                    ) {
                        Image(
                            painter = painterResource(song.preview),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio((painterResource(song.preview).intrinsicSize.width) / (painterResource(song.preview).intrinsicSize.height)
                                ), // Сохранить пропорции
                            //  contentScale = ContentScale.Fit

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
                .background(Color.Red)
                .fillMaxHeight()
                .fillMaxWidth()
         //   .height(400.dp) // Заполнение максимальной высоты
        ) {
            // images.drop(2).forEachIndexed { index, song ->
            Button(
                onClick = { /* TODO */ },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(paddingForCards)
                    //.weight(1f)
                    .clip(RoundedCornerShape(radiusForCards))
            ) {
                Box {
                    Image(
                        painter = painterResource(images[2].preview),
                        contentDescription = "",
                        modifier = Modifier
                            .aspectRatio(size2),
                            //.fillMaxWidth()
                            //.fillMaxHeight()

                           // .aspectRatio((painterResource(images[2].preview).intrinsicSize.width) / (painterResource(images[2].preview).intrinsicSize.height)
                           // ),
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
                   // .weight(1f)
                  //  .fillMaxSize()
                    .fillMaxHeight()
            ) {
                Box {
                    Image(
                        painter = painterResource(images[3].preview),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth().aspectRatio(size1),

                        contentScale = ContentScale.FillHeight
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