package com.vk.intership.products.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

interface DataSource {
    suspend fun fetchProductsJson(skip: Int, limit: Int): String
}

class DataSourceImpl : DataSource {
    private val client = OkHttpClient()

    override suspend fun fetchProductsJson(skip: Int, limit: Int): String = withContext(Dispatchers.IO) {
        val url = "https://dummyjson.com/products?skip=$skip&limit=$limit"
        val request: Request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IOException("Unexpected $response")
            }
            response.body?.string() ?: throw IOException("Failed to read response")
        }
    }

}