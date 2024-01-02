package com.gsu.vibe.composeComponents

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsu.vibe.paddingForCards
import com.gsu.vibe.radiusForCards

@Composable
fun SingleImageItem(images: List<Painter>) {

    Button(
        onClick = { /*TODO*/ },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(radiusForCards))
            .padding(paddingForCards)
            .clip(RoundedCornerShape(radiusForCards))

    )
    {
        Box {
            Image(
                painter = images[0],
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),

                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 15.dp, vertical = 15.dp),
                text = "234e234sdf3",
                textAlign = TextAlign.Right,
                //     fontFamily = firaSansFamily,
                fontSize = 18.sp

            )
        }
    }
}

