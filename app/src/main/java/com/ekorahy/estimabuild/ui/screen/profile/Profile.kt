package com.ekorahy.estimabuild.ui.screen.profile

import androidx.compose.runtime.Composable
import com.ekorahy.estimabuild.ui.components.ProfileContent

@Composable
fun Profile(
    navigateBack: () -> Unit,
) {
    ProfileContent(
        onBackClick = navigateBack
    )
}