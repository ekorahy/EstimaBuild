package com.ekorahy.estimabuild.ui.screen.estimation

import com.ekorahy.estimabuild.model.AddProduct

data class EstimationState(
    val addProduct: List<AddProduct>,
    val totalPrice: Double
)