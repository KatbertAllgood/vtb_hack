package com.example.vtb_hackathon.ui.theme

import androidx.compose.ui.graphics.Color

internal val textPalette = VTBColorsText(
    primary = Color(0xFF1F262E),
    secondary = Color(0xFF7B8196),
    negative = Color(0xFFF85741),
    accent = Color(0xFF0D69F2),
    contrast = Color(0xFFFBFCFF)
)

internal val backgroundPalette = VTBColorsBackground(
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFFF1F2F4),
    disable = Color(0xFFC5CAD3),
    accent = Color(0xFF0D69F2),
    accentTint = Color(0x1A1E65D9), //TODO("correct color?")
)

internal val strokePalette = VTBColorsStroke(
    primary = Color(0xFFE3E3E3),
    secondary = Color(0xFF7B8196),
    accent = Color(0xFF0D69F2),
    negative = Color(0xFFF85741),
)