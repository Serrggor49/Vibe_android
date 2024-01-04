package com.gsu.vibe.composeScreens.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.paddingForTextCards
import com.gsu.vibe.radiusForCards

@Composable
fun SingleImageItem(songs: List<SoundModel>, onClick:(name: String) -> Unit) {

    Button(
        onClick = { onClick(songs[0].name) },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(radiusForCards))
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))
    )
    {
        Box {
            Image(
                painter = painterResource(id = songs[0].preview),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(paddingForTextCards),
                text = "234e234sdf3",
                textAlign = TextAlign.Right,
                fontSize = 18.sp
            )
        }
    }
}

