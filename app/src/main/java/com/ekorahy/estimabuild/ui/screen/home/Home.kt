package com.ekorahy.estimabuild.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekorahy.estimabuild.di.Injection
import com.ekorahy.estimabuild.ui.common.UiState
import com.ekorahy.estimabuild.ui.components.HomeContent
import com.ekorahy.estimabuild.ui.screen.ViewModelFactory

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val query by rememberSaveable {
        viewModel.query
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProducts(query)
            }

            is UiState.Success -> {
                HomeContent(
                    products = uiState.data,
                    modifier = modifier,
                    query = query,
                    onQueryChange = viewModel::getProducts,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}
