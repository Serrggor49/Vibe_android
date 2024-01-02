package com.gsu.vibe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.gsu.vibe.composeComponents.QuadroImageItem
import com.gsu.vibe.composeComponents.SingleImageItem
import com.gsu.vibe.composeComponents.TripleImageItem
import kotlin.random.Random

class SleepComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyScreen()
            }
        }
    }

    @Preview
    @Composable
    fun MyScreen() {
        GradientBox {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(Color.Transparent)


                ) {
                    initHeaderBlock()
                    // initGeoEditBlock()
                    MosaicColumn(
                        listOf(
                            Item(
                                images = listOf(painterResource(id = R.drawable.sleep_03_01b_concentration_on_breathing_prev)),
                                type = ItemType.Single
                            ),
                            (Item(
                                images = listOf(
                                    painterResource(R.drawable.sleep_01_2b_endless_space_prev),
                                    painterResource(R.drawable.nature_01_1b_prev),
                                    painterResource(R.drawable.focus_01_1b_prev)
                                ), type = ItemType.Triple
                            )),
                            (Item(
                                images = listOf(
                                    painterResource(R.drawable.sleep_03_01b_concentration_on_breathing_prev),
                                    painterResource(R.drawable.sleep_01_2b_endless_space_prev),
                                    painterResource(R.drawable.nature_01_1b_prev),
                                    painterResource(R.drawable.focus_01_1b_prev),
                                ), type = ItemType.Quadruple
                            )),
                            (Item(
                                images = listOf(
                                    painterResource(R.drawable.sleep_01_2b_endless_space_prev),
                                    painterResource(R.drawable.nature_01_1b_prev),
                                    painterResource(R.drawable.focus_01_1b_prev)
                                ), type = ItemType.Triple
                            )),
                            (Item(
                                images = listOf(
                                    painterResource(R.drawable.sleep_01_2b_endless_space_prev),
                                    painterResource(R.drawable.nature_01_1b_prev),
                                    painterResource(R.drawable.focus_01_1b_prev)
                                ), type = ItemType.Triple
                            )),
                            (Item(
                                images = listOf(
                                    painterResource(R.drawable.sleep_01_2b_endless_space_prev),
                                    painterResource(R.drawable.nature_01_1b_prev),
                                    painterResource(R.drawable.focus_01_1b_prev)
                                ), type = ItemType.Triple
                            )),


                            )

                    )

                    //MosaicColumn(listOf(Item(images = listOf(painterResource(R.drawable.sleep_01_2b_endless_space_prev),painterResource(R.drawable.nature_01_1b_prev), painterResource(R.drawable.focus_01_1b_prev)), type = ItemType.Triple)))

                }

            }
        }
    }

    @Composable
    fun initHeaderBlock() {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(Color.Transparent)
                .fillMaxWidth()
                //  .padding(16.dp),
                .padding(start = 16.dp, end = 16.dp, top = 30.dp),

            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = 28.sp,
                lineHeight = 32.sp,
                text = stringResource(id = R.string.sleep_l),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )


            Button(
                onClick = { },
                // Убираем стандартный фон и тень у кнопки
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ad_delete),
                    contentDescription = null, // Описание для доступности
                    modifier = Modifier.size(30.dp) // Установите желаемый размер кнопки
                )
            }

            IconButton(
                onClick = {
                    Log.d("MyLogs49", "999999")
                },
                modifier = Modifier
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_favorites_bar),
                    contentDescription = "Описание иконки",
                    tint = Color.Unspecified // Не применять цветовой фильтр
                )
            }
        }
    }


    @Composable
    fun GradientBox(content: @Composable () -> Unit) {

        var size by remember { mutableStateOf(Size.Zero) } // Инициализация размера с нулевым значением

        val gradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF2A3572),
                Color(0xFF0B1130),
                Color(0xFF2A3572)
            ),
            start = Offset.Zero, // Начало в левом верхнем углу
            end = Offset(size.width, size.height) // Конец в правом нижнем углу
        )

        Box(
            modifier = Modifier
                .background(gradient)
                .onGloballyPositioned { coordinates ->
                    size = coordinates.size.toSize() // Получение и сохранение текущего размера
                }
                .fillMaxSize()
        ) {
            content()
        }
    }


    @Composable
    fun MosaicColumn(list: List<Item>) {
        LazyColumn {
            items(list) { item1 ->
                when (item1.type) {
                    ItemType.Single -> SingleImageItem(item1.images)
                    ItemType.Triple -> TripleImageItem(item1.images, leftSideSingleImage = Random.nextBoolean())
                    ItemType.Quadruple -> QuadroImageItem(item1.images)
               }
            }
        }
    }



    data class Item(
        val type: ItemType,
        // val image: Int, // Для типа Single
        val images: List<Painter> = emptyList(), // Для типов Triple и Quadruple
    )

    enum class ItemType {
        Single, Triple, Quadruple
    }

}


val firaSansFamily = FontFamily(
    Font(R.font.inter_semibold),
)
