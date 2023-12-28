package com.ekorahy.estimabuild.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekorahy.estimabuild.model.AddProduct

@Composable
fun HomeContent(
    products: List<AddProduct>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    ProductList(
        products = products,
        modifier = modifier,
        navigateToDetail = navigateToDetail
    )
}