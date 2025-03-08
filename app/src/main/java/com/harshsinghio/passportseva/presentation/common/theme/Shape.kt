package com.harshsinghio.passportseva.presentation.common.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val PassportSevaShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)

// Additional custom shapes
val RoundedCorner4 = RoundedCornerShape(4.dp)
val RoundedCorner8 = RoundedCornerShape(8.dp)
val RoundedCorner12 = RoundedCornerShape(12.dp)
val RoundedCorner16 = RoundedCornerShape(16.dp)
val RoundedCornerTopOnly = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
val RoundedCornerBottomOnly = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)