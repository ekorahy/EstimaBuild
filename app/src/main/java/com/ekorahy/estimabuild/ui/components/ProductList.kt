package com.ekorahy.estimabuild.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ekorahy.estimabuild.model.AddProduct

@Composable
fun ProductList(
    products: List<AddProduct>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (products.isEmpty()) {
        NotFound()
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(top = 80.dp)
            .testTag("ProductList")
    ) {
        items(products) { data ->
            ProductItem(
                image = data.product.image,
                title = data.product.title,
                category = data.product.category,
                price = data.product.price,
                modifier = modifier.clickable {
                    navigateToDetail(data.product.id)
                }
            )
        }
    }
}