package com.ekorahy.estimabuild.data

import com.ekorahy.estimabuild.model.AddProduct
import com.ekorahy.estimabuild.model.FakeProductDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ProductRepository {

    private val addProducts = mutableListOf<AddProduct>()

    init {
        if (addProducts.isEmpty()) {
            FakeProductDataSource.dummyProducts.forEach {
                addProducts.add(AddProduct(it, 0))
            }
        }
    }

    fun getAllProducts(): Flow<List<AddProduct>> {
        return flowOf(addProducts)
    }

    fun getProductById(productId: String): AddProduct {
        return addProducts.first {
            it.product.id == productId
        }
    }

    fun updateAddedProduct(productId: String, newCountValue: Int): Flow<Boolean> {
        val index = addProducts.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0) {
            val addProduct = addProducts[index]
            addProducts[index] =
                addProduct.copy(product = addProduct.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedProduct(): Flow<List<AddProduct>> {
        return getAllProducts()
            .map { addProducts ->
                addProducts.filter { addProduct ->
                    addProduct.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(): ProductRepository = instance ?: synchronized(this) {
            ProductRepository().apply {
                instance = this
            }
        }
    }
}