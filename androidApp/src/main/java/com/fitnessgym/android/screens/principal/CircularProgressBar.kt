package com.fitnessgym.android.screens.principal

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.floor
import kotlin.math.min

@Composable
internal fun CircularProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    strokeWidth: Float = 8f
) {
    val sweepAngle = remember(progress) { (progress/ 100)  * 360 }
    Log.d("CircularProgressBar", "progress $progress")
    Log.d("CircularProgressBar", "sweepAngle $sweepAngle")

    Canvas(modifier = modifier.size(57.dp)) {
        val diameter = min(size.width, size.height)

        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            size = Size(diameter, diameter)
        )
    }
}

 @Preview(showBackground = true)
@Composable
private fun CircularProgressBarPreview() {
    CircularProgressBar(progress = 1.5f)
}