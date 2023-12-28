package com.ekorahy.estimabuild.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekorahy.estimabuild.data.ProductRepository
import com.ekorahy.estimabuild.model.AddProduct
import com.ekorahy.estimabuild.model.Product
import com.ekorahy.estimabuild.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailProductViewModel(private val repository: ProductRepository): ViewModel() {
    val _uiState: MutableStateFlow<UiState<AddProduct>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<AddProduct>> get() = _uiState

    fun getProductById(productId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getProductById(productId))
        }
    }

    fun addProductToEstimation(product: Product, count: Int) {
        viewModelScope.launch {
            repository.updateAddedProduct(product.id, count)
        }
    }
}