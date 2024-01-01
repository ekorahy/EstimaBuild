package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme
import com.ekorahy.estimabuild.ui.theme.Slate100
import com.ekorahy.estimabuild.ui.theme.Slate400
import com.ekorahy.estimabuild.ui.theme.Shapes

@Composable
fun ProductItem(
    image: Int,
    title: String,
    category: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, Slate100), RoundedCornerShape(4.dp))
            .padding(16.dp)

    ) {
        Image(
            painter = painterResource(image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(160.dp)
                .height(100.dp)
                .clip(Shapes.medium)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.width(160.dp)
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .border(BorderStroke(0.4.dp, Slate400), RoundedCornerShape(4.dp))
                .padding(8.dp, 4.dp)
        )
        Spacer(modifier = modifier.size(10.dp))
        Text(
            text = stringResource(R.string.price, price),
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.align(Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    EstimaBuildTheme {
        ProductItem(
            image = R.drawable.mo1,
            title = "Monitor Samsung S24R350 24-inch FHD",
            category = "monitor",
            price = 68.07
        )
    }
}