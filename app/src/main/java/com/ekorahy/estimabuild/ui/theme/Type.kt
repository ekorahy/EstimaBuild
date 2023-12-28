package com.ekorahy.estimabuild.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R

// Set of Material typography styles to start with
val Typography: Typography
    get() = Typography(
        titleLarge = TextStyle(
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )

val robotoFont = FontFamily(
    Font(R.font.roboto_bold),
)

val poppinsFont = FontFamily(
    Font(R.font.poppins_extralight)
)