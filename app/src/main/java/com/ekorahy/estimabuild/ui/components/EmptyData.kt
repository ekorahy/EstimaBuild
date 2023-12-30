package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme

@Composable
fun EmptyData(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.empty_data),
                contentDescription = stringResource(R.string.empty_data_img),
                modifier = modifier
                    .padding(4.dp)
                    .size(160.dp)
            )
            Text(
                text = stringResource(R.string.empty_data),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.size(4.dp))
            TextButton(
                onClick = onClick
            ) {
                Text(
                    text = stringResource(R.string.add_data),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyDataPreview() {
    EstimaBuildTheme {
        EmptyData(
            onClick = {}
        )
    }
}