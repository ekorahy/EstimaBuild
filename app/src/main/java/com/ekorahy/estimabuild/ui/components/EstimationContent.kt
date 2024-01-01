package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekorahy.estimabuild.R
import com.ekorahy.estimabuild.ui.screen.estimation.EstimationState
import com.ekorahy.estimabuild.ui.theme.Slate700

@Composable
fun EstimationContent(
    state: EstimationState,
    onBackClick: () -> Unit,
    onProductCountChanged: (id: String, count: Int) -> Unit,
    onEstimateButtonClicked: (Double) -> Unit,
    navigateToHome: () -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                tint = Slate700,
                contentDescription = stringResource(R.string.back),
                modifier = modifier
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
                    .size(26.dp)
                    .clickable { onBackClick() }
                    .testTag(stringResource(R.string.back))
            )
            Text(
                text = stringResource(R.string.estimation),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 16.sp
                )
            )
        }
        if (state.addProduct.isEmpty()) {
            EmptyData(
                onClick = navigateToHome
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(0.dp, 16.dp, 0.dp, 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(weight = 1f)
                    .testTag(stringResource(R.string.product_list))
            ) {
                items(state.addProduct, key = { it.product.id }) { item ->
                    EstimationItem(
                        productId = item.product.id,
                        image = item.product.image,
                        title = item.product.title,
                        category = item.product.category,
                        price = item.product.price,
                        totalPrice = item.product.price * item.count,
                        count = item.count,
                        onProductCountChanged = onProductCountChanged,
                        modifier = modifier.clickable {
                            navigateToDetail(item.product.id)
                        }
                    )
                }
            }
        }

        ButtonAdd(
            text = stringResource(R.string.estimate_now),
            enabled = state.addProduct.isNotEmpty(),
            onClick = {
                onEstimateButtonClicked(state.totalPrice)
            },
            modifier = modifier.testTag(stringResource(R.string.estimate_now))
        )
    }
}