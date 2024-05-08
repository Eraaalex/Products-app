package com.vk.intership.products.data

import android.util.Log
import com.vk.intership.products.model.Product
import com.vk.intership.products.network.DataSource
import com.vk.intership.products.network.DataSourceImpl
import com.vk.intership.products.network.ProductsResponse
import kotlinx.serialization.json.Json

class ProductRepository(private val dataSource: DataSource) {

    suspend fun getProducts(skip: Int, limit: Int): List<Product> {
        return readJsonData(skip, limit)?.products ?: listOf()
    }

    private suspend fun readJsonData(skip: Int, limit: Int): ProductsResponse? {
        val jsonString = dataSource.fetchProductsJson(skip, limit)
        Log.e("MyApp", "[Repo] jsonString: $jsonString")
        return try {
            Json.decodeFromString<ProductsResponse>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}