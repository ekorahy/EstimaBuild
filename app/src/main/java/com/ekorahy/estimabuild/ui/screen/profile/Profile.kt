package com.ekorahy.estimabuild.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekorahy.estimabuild.ui.components.ProfileContent

@Composable
fun Profile(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileContent(
        onBackClick = navigateBack
    )
}