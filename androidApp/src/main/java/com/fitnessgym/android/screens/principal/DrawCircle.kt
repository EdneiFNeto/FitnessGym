package com.fitnessgym.android.screens.principal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun DrawCircle() {
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .border(
                BorderStroke(2.dp, Color.LightGray),
                CircleShape
            )
            .clip(CircleShape)
    )
}
