package com.ekorahy.estimabuild.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekorahy.estimabuild.di.Injection
import com.ekorahy.estimabuild.ui.common.UiState
import com.ekorahy.estimabuild.ui.components.DetailContent
import com.ekorahy.estimabuild.ui.screen.ViewModelFactory

@Composable
fun DetailProduct(
    productId: String,
    viewModel: DetailProductViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToEstimate: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductById(productId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.product.image,
                    title = data.product.title,
                    category = data.product.category,
                    price = data.product.price,
                    desc = data.product.desc,
                    count = data.count,
                    onBackClick = navigateBack,
                    onAddToEstimate = { count ->
                        viewModel.addProductToEstimation(data.product, count.toInt())
                        navigateToEstimate()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}