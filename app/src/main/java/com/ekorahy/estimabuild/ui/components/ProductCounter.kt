package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R

@Composable
fun ProductCounter(
    addId: String,
    addCount: Int,
    onProductIncreased: (String) -> Unit,
    onProductDecreased: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 40.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "—",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased(addId)
                    }
                    .testTag(stringResource(R.string.button_decrease))
            )
        }
        Text(
            text = addCount.toString(),
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "＋",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased(addId)
                    }
                    .testTag(stringResource(R.string.button_increase))
            )
        }
    }
}