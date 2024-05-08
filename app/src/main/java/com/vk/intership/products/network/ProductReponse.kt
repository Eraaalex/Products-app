package com.vk.intership.products.network

import com.vk.intership.products.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)