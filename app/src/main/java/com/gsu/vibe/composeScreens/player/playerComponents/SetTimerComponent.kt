package com.gsu.vibe.composeScreens.player.playerComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.screens.findActivity
import com.gsu.vibe.firaSansFamily
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerFocusVertical
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState

private val colorDivider = Color(0x26FFFFFF)
private val wheelPickerWidth = 72.dp  // for each component
private val backColor = listOf(
    Color(0xFF2A3572),
    Color(0xFF0B1130),
    Color(0xFF0B1130),
    Color(0xFF0B1130),
    Color(0xFF2A3572)
)
private val headerText = "Выбрать\n продолжительность"

@Composable
@Preview
fun SetTimerComponent() {

    val viewModel: MediaPlayerComposeViewModel = viewModel(LocalContext.current.findActivity()!!)

    var timeInHours = 0
    var timeInMinutes = 10
    var timeInSeconds = 0

    val state = viewModel.state.collectAsState()
    var showDialog by remember {
        mutableStateOf(state.value.currentTrackTime == 0 && state.value.durationInMs.toInt() == 0) // нужно для скрытия таймера если плеер уже работает
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = backColor,
                            start = Offset.Zero, // Начало в левом верхнем углу
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        )
                    )

            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = headerText,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        fontFamily = firaSansFamily,
                        fontSize = 18.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(35.dp))

                    val stateHours = rememberFWheelPickerState(0)
                    val stateMinutes = rememberFWheelPickerState(10)
                    val stateSeconds = rememberFWheelPickerState(0)
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        FVerticalWheelPicker(
                            // часов
                            modifier = Modifier.width(wheelPickerWidth),
                            count = 23,
                            state = stateHours,
                            focus = {
                                Text(
                                    text = "h", modifier = Modifier
                                        .align(Alignment.Top)
                                        .padding(bottom = 0.dp, start = 40.dp),
                                    fontSize = 13.sp,
                                    color = Color(0xFFFFFFFF)
                                )
                                FWheelPickerFocusVertical(
                                    dividerColor = colorDivider,
                                    dividerSize = 1.dp
                                )
                            },
                        ) { index ->
                            timeInHours = this.state.currentIndex
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 18.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }

                        FVerticalWheelPicker(
                            // минут
                            modifier = Modifier.width(wheelPickerWidth),
                            count = 59,
                            state = stateMinutes,
                            focus = {
                                Text(
                                    text = "m", modifier = Modifier
                                        .align(Alignment.Top)
                                        .padding(bottom = 0.dp, start = 40.dp),
                                    fontSize = 13.sp,
                                    color = Color(0xFFFFFFFF)
                                )

                                FWheelPickerFocusVertical(
                                    dividerColor = colorDivider,
                                    dividerSize = 1.dp
                                )

                            },
                        ) { index ->
                            timeInMinutes = this.state.currentIndex
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 18.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }

                        FVerticalWheelPicker(
                            // секунд
                            modifier = Modifier.width(wheelPickerWidth),
                            count = 59,
                            state = stateSeconds,
                            focus = {
                                Text(
                                    text = "s", modifier = Modifier
                                        .align(Alignment.Top)
                                        .padding(bottom = 0.dp, start = 40.dp),
                                    fontSize = 13.sp,
                                    color = Color(0xFFFFFFFF)
                                )

                                FWheelPickerFocusVertical(
                                    // dividerColor = colorDivider,
                                    dividerSize = 1.dp
                                )

                            },
                        ) { index ->
                            timeInSeconds = this.state.currentIndex
                            Text(
                                text = index.toString(),
                                fontFamily = firaSansFamily,
                                fontSize = 18.sp,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(35.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF035ADD), Color(0xFF03D0DD)),
                                    start = Offset.Zero, // Начало в левом верхнем углу
                                    end = Offset(1000f, 1000f)
                                )
                            )
                            .clickable(onClick = {
                                showDialog = false
                                viewModel.setDurationTime(
                                    timeInHours = timeInHours,
                                    timeInMinutes = timeInMinutes,
                                    timeInSeconds = timeInSeconds
                                )
                            })
                    ) {
                        Text(
                            text = "Начать",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(
                                Color(0xFF000429)
                            )
                            .clickable(onClick = { showDialog = false })
                    ) {
                        Text(
                            text = "Закрыть",
                            color = Color(0x99FFFFFF),
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }
        }
    }
}


