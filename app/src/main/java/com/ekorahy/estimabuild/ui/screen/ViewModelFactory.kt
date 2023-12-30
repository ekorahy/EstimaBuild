package com.ekorahy.estimabuild.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekorahy.estimabuild.data.ProductRepository
import com.ekorahy.estimabuild.ui.screen.detail.DetailProductViewModel
import com.ekorahy.estimabuild.ui.screen.estimation.EstimationViewModel
import com.ekorahy.estimabuild.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(EstimationViewModel::class.java)) {
            return EstimationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel class : " + modelClass.name)
    }
}