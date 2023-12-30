package com.ekorahy.estimabuild.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekorahy.estimabuild.data.ProductRepository
import com.ekorahy.estimabuild.model.AddProduct
import com.ekorahy.estimabuild.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<AddProduct>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<AddProduct>>> get() = _uiState

    fun getProducts() {
        viewModelScope.launch {
            repository.getAllProducts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { addedProduct ->
                    _uiState.value = UiState.Success(addedProduct)
                }
        }
    }
}