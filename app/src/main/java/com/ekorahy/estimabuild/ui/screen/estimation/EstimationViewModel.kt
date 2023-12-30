package com.ekorahy.estimabuild.ui.screen.estimation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekorahy.estimabuild.data.ProductRepository
import com.ekorahy.estimabuild.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EstimationViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<EstimationState>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<EstimationState>> get() = _uiState

    fun getAddedProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedProduct()
                .collect { addProduct ->
                    val totalPrice = addProduct.sumOf { it.product.price * it.count }
                    _uiState.value = UiState.Success(EstimationState(addProduct, totalPrice))
                }
        }
    }

    fun updateAddedProduct(productId: String, count: Int) {
        viewModelScope.launch {
            repository.updateAddedProduct(productId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedProducts()
                    }
                }
        }
    }
}