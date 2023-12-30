package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme
import com.ekorahy.estimabuild.ui.theme.Slate100
import com.ekorahy.estimabuild.ui.theme.Slate400
import com.ekorahy.estimabuild.ui.theme.Slate700

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                tint = Slate700,
                contentDescription = stringResource(R.string.back),
                modifier = modifier
                    .padding(0.dp, 16.dp, 16.dp, 16.dp)
                    .size(26.dp)
                    .clickable { onBackClick() }
            )
            Text(
                text = stringResource(R.string.profile),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 16.sp
                )
            )
        }
        Row (
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = stringResource(R.string.profile_img),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(160.dp)
                        .border(6.dp, Slate100, CircleShape)
                        .padding(10.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = modifier.size(6.dp))
                Text(
                    text = stringResource(R.string.profile_name),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = modifier.size(2.dp))
                Text(
                    text = stringResource(R.string.profile_email),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Slate400
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    EstimaBuildTheme {
        ProfileContent(
            onBackClick = {}
        )
    }
}