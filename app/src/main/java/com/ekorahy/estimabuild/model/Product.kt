package com.ekorahy.estimabuild.model

data class Product(
    val id: String,
    val image: Int,
    val title: String,
    val category: String,
    val price: Double,
    val desc: String
)