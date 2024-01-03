package com.gsu.vibe.composeScreens.composeComponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsu.vibe.R


// блок в котором находится название экрана (для сна, медитации и т.д.) и кнопки
// скрытия рекламы и переход в избранное

//val firaSansFamily = FontFamily(Font(R.font.inter_semibold))
val firaSansFamily = FontFamily(Font(R.font.inter_regular))

@Preview
@Composable
fun InitHeaderBlock() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 30.dp),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            fontSize = 28.sp,
            lineHeight = 32.sp,
            text = stringResource(id = R.string.sleep_l),
            fontWeight = FontWeight(400),
            fontFamily = firaSansFamily,
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