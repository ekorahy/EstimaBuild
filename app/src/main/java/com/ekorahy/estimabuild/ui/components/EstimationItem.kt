package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ekorahy.estimabuild.ui.theme.Shapes
import com.ekorahy.estimabuild.ui.theme.Slate100
import com.ekorahy.estimabuild.ui.theme.Slate400

@Composable
fun EstimationItem(
    productId: String,
    image: Int,
    title: String,
    category: String,
    price: Double,
    totalPrice: Double,
    count: Int,
    onProductCountChanged: (id: String, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(BorderStroke(2.dp, Slate100), RoundedCornerShape(4.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(90.dp)
                .width(110.dp)
                .clip(Shapes.small)
        )
        Spacer(modifier = modifier.size(6.dp))
        Column {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = category,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Slate400
                ),
                modifier = modifier
                    .border(BorderStroke(0.4.dp, Slate400), RoundedCornerShape(4.dp))
                    .padding(4.dp, 2.dp)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.multiplication_price, count, price),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier.padding(2.dp)
                    )
                    Text(
                        text = stringResource(R.string.price, totalPrice),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier.padding(2.dp)
                    )
                }
                ProductCounter(
                    addId = productId,
                    addCount = count,
                    onProductIncreased = { onProductCountChanged(productId, count + 1) },
                    onProductDecreased = { onProductCountChanged(productId, count - 1) },
                    modifier = modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EstimationItemPreview() {
    EstimaBuildTheme {
        EstimationItem(
            productId = "product-1",
            image = R.drawable.mo1,
            title = "Monitor Samsung S24R350 24-inch FHD",
            category = "monitor",
            price = 68.07,
            totalPrice = 68.07,
            count = 1,
            onProductCountChanged = { _, _ -> }
        )
    }
}