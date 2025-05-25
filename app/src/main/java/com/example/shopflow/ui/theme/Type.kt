package com.example.shopflow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shopflow.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

/**
 * Font family for the Tangerine font.
 * This font family contains the Tangerine font.
 */
val tangarineFamily = FontFamily(
    Font(R.font.tangerine)
)

/**
 * Font family for the Century Old Style Std Bold font.
 * This font family contains the Century Old Style Std Bold font.
 */
val centuryOldStyleStdBoldFamily = FontFamily(
    Font(R.font.century_old_style_std_bold)
)

/**
 * Font family for the Neuzeit SLT Std Book font.
 * This font family contains the Neuzeit SLT Std Book font.
 */
val neuzeitSltStdBookFamily = FontFamily(
    Font(R.font.neuzeit_slt_std_book)
)

/**
 * Font family for the Neuzeit S Book font.
 * This font family contains the Neuzeit S Book font.
 */
val neuzeitSBookFamily = FontFamily(
    Font(R.font.neuzeit_s_book)
)