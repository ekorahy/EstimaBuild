package com.ekorahy.estimabuild.data

import com.ekorahy.estimabuild.model.AddProduct
import com.ekorahy.estimabuild.model.FakeProductDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ProductRepository {

    private val estimateProducts = mutableListOf<AddProduct>()

    init {
        if (estimateProducts.isEmpty()) {
            FakeProductDataSource.dummyProducts.forEach {
                estimateProducts.add(AddProduct(it, 0))
            }
        }
    }

    fun getProducts(): Flow<List<AddProduct>> {
        return flowOf(estimateProducts)
    }

    fun getProductById(productId: String): AddProduct {
        return estimateProducts.first {
            it.product.id == productId
        }
    }

    fun updateAddedProduct(productId: String, newCountValue: Int): Flow<Boolean> {
        val index = estimateProducts.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0) {
            val addedProduct = estimateProducts[index]
            estimateProducts[index] =
                addedProduct.copy(product = addedProduct.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedProduct(): Flow<List<AddProduct>> {
        return getProducts()
            .map { addedProducts ->
                addedProducts.filter { addedProduct ->
                    addedProduct.count != 0
                }
            }
    }

    companion object
    @Volatile
    private var instance: ProductRepository? = null

    fun getInstance(): ProductRepository = instance ?: synchronized(this) {
        ProductRepository().apply {
            instance = this
        }
    }
}