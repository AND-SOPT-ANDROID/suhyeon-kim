package org.sopt.and.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class WavveColors(
    //Gray
    val BackgroundGray: Color = Color(0xFF1B1B1B),
    val Gray71: Color = Color(0xFF717171),
    val Gray2F: Color = Color(0xFF2F2F2F),
    val Gray25: Color = Color(0xFF252525),

    //Blue
    val VividBlue: Color = Color(0xFF1353FA),
)

val LocalColors = staticCompositionLocalOf { WavveColors() }

