package com.ekorahy.estimabuild.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme
import com.ekorahy.estimabuild.ui.theme.Slate100
import com.ekorahy.estimabuild.ui.theme.Slate400
import com.ekorahy.estimabuild.ui.theme.Slate700

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    category: String,
    price: Double,
    desc: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToEstimate: (count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var totalPrice by rememberSaveable {
        mutableStateOf(0.0)
    }

    var addCount by rememberSaveable {
        mutableStateOf(count)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = Slate700,
            contentDescription = stringResource(R.string.back),
            modifier = modifier
                .padding(0.dp, 8.dp)
                .size(26.dp)
                .clickable { onBackClick() }
        )
        Image(
            painter = painterResource(image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = modifier.size(10.dp))
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
                    .border(BorderStroke(0.4.dp, Slate400), RoundedCornerShape(4.dp))
                    .padding(8.dp, 4.dp)
            )
            Text(
                text = "$ $price",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp
                ),
                modifier = modifier.padding(16.dp, 0.dp)
            )
        }
        Spacer(modifier = modifier.size(10.dp))
        Text(
            text = stringResource(R.string.desc),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = modifier.size(6.dp))
        Text(
            text = desc,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = modifier.size(12.dp))
        Button(
            onClick = { onAddToEstimate(addCount) },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .semantics(mergeDescendants = true) {
                    contentDescription = "button add product to estimation"
                }
        ) {
            Text(
                text = stringResource(R.string.add_to_estimation),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 16.sp,
                    color = Slate100
                ),
                modifier = modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    EstimaBuildTheme {
        DetailContent(
            image = R.drawable.mo1,
            title = "Monitor Samsung S24R350 24-inch FHD",
            category = "monitor",
            price = 68.07,
            desc = "Samsung S24R350 24-inch FHD Monitor with bezel-less design is a 24\" monitor with IPS display panel. This monitor is supported with 1920 x 1080pixels resolution with 16:9 aspect ratio and 75Hz refresh rate. With its various sophistication, the Samsung S24R350 24-inch FHD Monitor with bezel-less design is suitable for various purposes.\n" +
                    "2.  Monitor Xiaomi 30-inch Curved Gaming.",
            count = 1,
            onBackClick = { },
            onAddToEstimate = { },
            modifier = Modifier
        )
    }
}