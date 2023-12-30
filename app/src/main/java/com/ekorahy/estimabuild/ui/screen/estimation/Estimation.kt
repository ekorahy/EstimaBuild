package com.ekorahy.estimabuild.ui.screen.estimation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekorahy.estimabuild.di.Injection
import com.ekorahy.estimabuild.ui.common.UiState
import com.ekorahy.estimabuild.ui.components.EstimationContent
import com.ekorahy.estimabuild.ui.screen.ViewModelFactory

@Composable
fun Estimation(
    navigateBack: () -> Unit,
    viewModel: EstimationViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    onEstimateButtonClicked: (Double) -> Unit,
    navigateToHome: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedProducts()
            }

            is UiState.Success -> {
                EstimationContent(
                    state = uiState.data,
                    onBackClick = navigateBack,
                    onProductCountChanged = { productId, count ->
                        viewModel.updateAddedProduct(productId, count)
                    },
                    onEstimateButtonClicked = onEstimateButtonClicked,
                    navigateToHome = navigateToHome,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}