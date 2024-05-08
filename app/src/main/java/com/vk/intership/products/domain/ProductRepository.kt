package com.vk.intership.products.domain

import com.vk.intership.products.domain.model.Product
import com.vk.intership.products.data.network.DataSource
import com.vk.intership.products.domain.model.ProductsResponse
import kotlinx.serialization.json.Json

class ProductRepository(private val dataSource: DataSource) {

    suspend fun getProducts(skip: Int, limit: Int): List<Product> {
        return readJsonData(skip, limit)?.products ?: listOf()
    }

    private suspend fun readJsonData(skip: Int, limit: Int): ProductsResponse? {
        val jsonString = dataSource.fetchProductsJson(skip, limit)
        return try {
            Json.decodeFromString<ProductsResponse>(jsonString)
        } catch (e: Exception) {
            null
        }
    }

}