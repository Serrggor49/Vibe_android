package com.gsu.vibe.composeScreens.composeComponents.playerComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gsu.vibe.firaSansFamily
import kotlin.math.abs

@Composable
@Preview
fun SetTimerComponent() {

    var showDialog by remember { mutableStateOf(true) }


    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(modifier = Modifier
                //.fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Blue)

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

                    val items = (1..10).map { it.toString() } // Пример списка элементов
                    WheelPicker(data = items) { selectedItem ->
                        println("Выбранный элемент: $selectedItem")
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


@Composable
fun WheelPicker(
    data: List<String>, // Данные для пикера
    onItemSelected: (String) -> Unit // Лямбда, вызываемая при выборе элемента
) {
    val itemHeight = 40.dp // Высота одного элемента
    val visibleItemCount = 5 // Количество видимых элементов

    LazyColumn(
        modifier = Modifier.size(width = 100.dp, height = itemHeight * visibleItemCount),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(data) { index, item ->
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        // Расчет масштаба и прозрачности в зависимости от положения элемента
                        val itemCenter = (index + 0.5f) * itemHeight.value
                        val lazyListCenter = itemHeight.value * visibleItemCount / 2
                        val distanceToCenter = abs(lazyListCenter - itemCenter)
                        val rawScale = 1f - (distanceToCenter / lazyListCenter)
                        val scale = rawScale.coerceIn(0.5f, 1f)
                        scaleX = scale
                        scaleY = scale
                        alpha = scale
                    }
                    .size(height = itemHeight, width = 100.dp)
            ) {
                Text(text = item, fontSize = 20.sp)
            }
        }
    }
}

// Пример использования WheelPicker
@Composable
fun WheelPickerExample() {
    val items = (1..10).map { it.toString() } // Пример списка элементов
    WheelPicker(data = items) { selectedItem ->
        println("Выбранный элемент: $selectedItem")
    }
}

