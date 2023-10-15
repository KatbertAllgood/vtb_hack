package com.example.vtb_hackathon.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class VTBColorsText(
    val primary: Color,
    val secondary: Color,
    val negative: Color,
    val accent: Color,
    val contrast: Color,
)

data class VTBColorsBackground(
    val primary: Color,
    val secondary: Color,
    val disable: Color,
    val accent: Color,
    val accentTint: Color,
)

data class VTBColorsStroke(
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val negative: Color,
)

data class VTBTypography(
    val robotoRegular: TextStyle,
    val robotoMedium: TextStyle,
)

data class VTBShape(
    val padding: Dp,
    val cornersStyle: Shape
)

object VTBTheme {
    internal val textColors: VTBColorsText
        @Composable
        internal get() = LocalVTBColorsText.current

    internal val backgroundColors: VTBColorsBackground
        @Composable
        internal get() = LocalVTBColorsBackground.current

    internal val strokeColors: VTBColorsStroke
        @Composable
        internal get() = LocalVTBColorsStroke.current

    internal val typography: VTBTypography
        @Composable
        internal get() = LocalVTBTypography.current

    internal val shape: VTBShape
        @Composable
        internal get() = LocalVTBShape.current
}

enum class VTBSize {
    Small, Medium, Big
}

enum class VTBCorners {
    Flat, Rounded
}


internal val LocalVTBColorsText = staticCompositionLocalOf<VTBColorsText>{
    error("No colors provided")
}

internal val LocalVTBColorsBackground = staticCompositionLocalOf<VTBColorsBackground>{
    error("No colors provided")
}

internal val LocalVTBColorsStroke = staticCompositionLocalOf<VTBColorsStroke>{
    error("No colors provided")
}

internal val LocalVTBTypography = staticCompositionLocalOf<VTBTypography> {
    error("No fonts provided")
}

internal val LocalVTBShape = staticCompositionLocalOf<VTBShape> {
    error("No shapes provided")
}