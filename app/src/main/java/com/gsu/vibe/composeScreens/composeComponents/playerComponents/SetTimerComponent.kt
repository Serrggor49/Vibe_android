package com.gsu.vibe.composeScreens.composeComponents.playerComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gsu.vibe.firaSansFamily
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerFocusVertical
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import kotlin.math.abs

@Composable
@Preview
fun SetTimerComponent() {



    var showDialog by remember { mutableStateOf(true) }


    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {



            BoxWithConstraints(
                modifier = Modifier
                    //.fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF0B1130), Color(0xFF0B1130), Color(0xFF0B1130),  Color(0xFF2A3572)),
                            start = Offset.Zero, // Начало в левом верхнем углу
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY) // Растягиваем градиент на весь возможный размер
                        ))

            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Выбрать\n" +
                                "продолжительность",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        fontFamily = firaSansFamily,
                        fontSize = 14.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )

                    val state = rememberFWheelPickerState(10)
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        FVerticalWheelPicker(

                            modifier = Modifier.width(60.dp),
                            count = 24,
                            state = state,
                            focus = {
                                Text(text = "h", modifier = Modifier
                                    .align(Alignment.Top)
                                    .padding(bottom = 0.dp, start = 40.dp),
                                    fontSize = 13.sp,
                                    color = Color(0xFFFFFFFF))
                                FWheelPickerFocusVertical(
                                    dividerColor = Color.LightGray,
                                    dividerSize = 1.dp
                                )

                            },
                        ) { index ->
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 18.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }

                        FVerticalWheelPicker(
                            modifier = Modifier.width(60.dp),
                            count = 50,
                            focus = {
                                Text(text = "m", modifier = Modifier
                                    .align(Alignment.Top)
                                    .padding(bottom = 2.dp, start = 40.dp))
                                FWheelPickerFocusVertical(
                                    dividerColor = Color.Red,
                                    dividerSize = 1.dp
                                )

                            },
                        ) { index ->
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 14.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }

                        FVerticalWheelPicker(
                            modifier = Modifier.width(60.dp),
                            count = 50,
                            focus = {
                                Text(text = "s", modifier = Modifier
                                    .align(Alignment.Top)
                                    .padding(bottom = 2.dp, start = 40.dp))
                                FWheelPickerFocusVertical(
                                    dividerColor = Color.Red,
                                    dividerSize = 1.dp
                                )

                            },
                        ) { index ->
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 14.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }
                    }



                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = { showDialog = false }
                    ) {
                        Text("Закрыть")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }
        }
    }
}

// https://androidexample365.com/compose-wheel-picker-with-kotlin/ вот его надо добавить


