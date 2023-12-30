package com.ekorahy.estimabuild.di

import com.ekorahy.estimabuild.data.ProductRepository

object Injection {
    fun provideRepository(): ProductRepository {
        return ProductRepository.getInstance()
    }
}