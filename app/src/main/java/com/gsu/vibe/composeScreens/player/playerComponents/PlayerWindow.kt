package com.gsu.vibe.composeScreens.player.playerComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Slider

import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gsu.vibe.R
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.composeScreens.player.playerComponents.SliderForPlayer
import com.gsu.vibe.composeScreens.screens.findActivity
import com.gsu.vibe.firaSansFamily
import com.gsu.vibe.getFormtTime
import com.gsu.vibe.presentation.MainViewModel
import com.gsu.vibe.radiusForPlayer


// полупрозрачное окно для управления плеером и отображения его стейта
@Composable
@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
fun PlayerWindow(testName: String = "testName") {

//    val mainViewModel: MainViewModel = viewModel()
//    mainViewModel.timeForMixerPlayerInMs = 1221
//    mainViewModel.startTest()

    val viewModelStoreOwner = LocalContext.current.findActivity()!!
    val viewModel: MediaPlayerComposeViewModel =
        viewModel(viewModelStoreOwner) // Теперь мы можем безопасно использовать viewModelStoreOwner, так как уверены, что он не null
    val state = viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(radiusForPlayer))
                .background(Color(android.graphics.Color.parseColor("#80000000")))
        )
        {
            Column {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start)
                                .padding(start = 14.dp, top = 11.dp),
                            text = (stringResource(id = state.value.title)).replace("\n", " "),
                            fontWeight = FontWeight(400),
                            fontFamily = firaSansFamily,
                            fontSize = 18.sp,
                            color = Color(0xFFFFFFFF),
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 14.dp, top = 0.dp),
                            fontWeight = FontWeight(400),
                            fontFamily = firaSansFamily,
                            fontSize = 14.sp,
                            color = Color(0xFFFFFFFF),
                            text = stringResource(id = state.value.subtitle)
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(top = 11.dp, end = 13.dp)
                            .size(height = 50.dp, width = 50.dp)
                            .align(Alignment.Top)
                            .alpha(0.7f),
                        elevation = ButtonDefaults.elevation( // убирает тень
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),

                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        )

                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.ic_play_button),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

                SliderForPlayer()

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 14.dp, top = 0.dp),
                        fontWeight = FontWeight(400),
                        fontFamily = firaSansFamily,
                        fontSize = 14.sp,
                        color = Color(0xFFFFFFFF),
                        text = "00:00"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 14.dp, top = 0.dp),
                        fontWeight = FontWeight(400),
                        fontFamily = firaSansFamily,
                        fontSize = 14.sp,
                        color = Color(0xFFFFFFFF),
                        text = getFormtTime(state.value.durationInMs)
                    )
                }

            }

        }
    }

}

