package com.ellison.composemovie.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    // small = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(38.dp, 38.dp, 38.dp, 38.dp),
    large = RoundedCornerShape(6.dp)
)

val editShapes = Shapes(
    // small = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(18.dp, 4.dp, 4.dp, 4.dp),
    medium = RoundedCornerShape(38.dp, 4.dp, 4.dp, 4.dp),
    large = RoundedCornerShape(6.dp, 6.dp, 0.dp, 0.dp)
)
