package com.ekorahy.estimabuild.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekorahy.estimabuild.model.AddProduct

@Composable
fun HomeContent(
    products: List<AddProduct>,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
    )
    ProductList(
        products = products,
        modifier = modifier,
        navigateToDetail = navigateToDetail
    )
}