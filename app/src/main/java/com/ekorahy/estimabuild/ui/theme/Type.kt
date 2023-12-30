package com.ekorahy.estimabuild.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.theme.Slate950

// Set of Material typography styles to start with
val Typography: Typography
    get() = Typography(
        titleLarge = TextStyle(
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            color = Slate950
        ),
        titleMedium = TextStyle(
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            color = Slate950
        ),
        titleSmall = TextStyle(
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.sp,
            color = Slate950
        ),
        bodyLarge = TextStyle(
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp,
            color = Slate950
        ),
        bodyMedium = TextStyle(
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = Slate950
        ),
        bodySmall = TextStyle(
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.2.sp,
            color = Gold
        )
    )

val robotoFont = FontFamily(
    Font(R.font.roboto_bold),
)

val poppinsFont = FontFamily(
    Font(R.font.poppins_extralight)
)