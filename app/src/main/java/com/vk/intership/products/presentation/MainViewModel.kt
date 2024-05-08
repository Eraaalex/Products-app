package com.vk.intership.products.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.intership.products.domain.ProductRepository
import com.vk.intership.products.domain.model.Product
import kotlinx.coroutines.launch


class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private var currentPage = 0
    private val pageSize = 20


    fun loadProducts() {
        if (_isLoading.value == true) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val data = repository.getProducts(currentPage * pageSize, pageSize)
                val currentProducts = _products.value ?: listOf()
                _products.postValue(currentProducts + data)
                currentPage++
                _isLoading.postValue(false)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failed to load products", e)
                _products.postValue(emptyList())
                _isLoading.postValue(false)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

}
