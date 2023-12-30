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
                    data.product.image,
                    data.product.id,
                    data.product.title,
                    data.product.category,
                    data.product.price,
                    data.product.desc,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToEstimate = { addCount ->
                        viewModel.addProductToEstimation(data.product, addCount)
                        navigateToEstimate()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}