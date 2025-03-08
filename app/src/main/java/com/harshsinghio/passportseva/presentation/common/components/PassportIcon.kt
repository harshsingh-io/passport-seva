package com.harshsinghio.passportseva.presentation.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * A custom composable that draws a passport logo icon
 *
 * @param modifier Modifier to be applied to the canvas
 * @param tint The color to use for the icon
 */
@Composable
fun PassportIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Canvas(modifier = modifier.size(24.dp)) {
        val width = size.width
        val height = size.height
        val strokeWidth = size.width / 12

        // Passport Book Outline
        val path = Path().apply {
            moveTo(width * 0.2f, height * 0.1f)
            lineTo(width * 0.8f, height * 0.1f)
            lineTo(width * 0.8f, height * 0.9f)
            lineTo(width * 0.2f, height * 0.9f)
            close()
        }

        drawPath(
            path = path,
            color = tint,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Spine of the passport
        drawLine(
            color = tint,
            start = Offset(width * 0.2f, height * 0.1f),
            end = Offset(width * 0.2f, height * 0.9f),
            strokeWidth = strokeWidth
        )

        // Horizontal lines (pages)
        drawLine(
            color = tint,
            start = Offset(width * 0.5f, height * 0.6f),
            end = Offset(width * 0.7f, height * 0.6f),
            strokeWidth = strokeWidth
        )

        drawLine(
            color = tint,
            start = Offset(width * 0.5f, height * 0.75f),
            end = Offset(width * 0.65f, height * 0.75f),
            strokeWidth = strokeWidth
        )

        // Passport header rectangle
        drawRect(
            color = tint,
            topLeft = Offset(width * 0.3f, height * 0.25f),
            size = Size(width * 0.4f, height * 0.2f)
        )
    }
}