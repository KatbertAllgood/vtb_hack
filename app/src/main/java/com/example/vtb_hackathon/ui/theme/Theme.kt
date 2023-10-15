package com.example.vtb_hackathon.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R

@Composable
internal fun VTBTheme(
    textSize: VTBSize = VTBSize.Medium,
    paddingSize: VTBSize = VTBSize.Medium,
    corners: VTBCorners = VTBCorners.Rounded,
    content: @Composable () -> Unit
) {

    val textColors = textPalette
    val backgroundColors = backgroundPalette
    val strokeColors = strokePalette

    val typography = VTBTypography(
        robotoRegular = TextStyle(
            fontSize = when (textSize) {
                VTBSize.Small -> 14.sp
                VTBSize.Medium -> 16.sp
                VTBSize.Big -> 20.sp
            },
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        ),
        robotoMedium = TextStyle(
            fontSize = when (textSize) {
                VTBSize.Small -> 14.sp
                VTBSize.Medium -> 16.sp
                VTBSize.Big -> 20.sp
            },
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        ),
    )

    val shapes = VTBShape(
        padding = when (paddingSize) {
            VTBSize.Small -> 12.dp
            VTBSize.Medium -> 16.dp
            VTBSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            VTBCorners.Flat -> RoundedCornerShape(0.dp)
            VTBCorners.Rounded -> RoundedCornerShape(10.dp)
        }
    )

    CompositionLocalProvider(
        LocalVTBColorsText provides textColors,
        LocalVTBColorsBackground provides backgroundColors,
        LocalVTBColorsStroke provides strokeColors,
        LocalVTBTypography provides typography,
        LocalVTBShape provides shapes,
        content = content
    )

}