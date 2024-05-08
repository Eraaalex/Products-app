package com.vk.intership.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vk.intership.products.adapters.ProductAdapter
import com.vk.intership.products.data.ProductRepository
import com.vk.intership.products.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

        private lateinit var recyclerView: RecyclerView
        private lateinit var productAdapter: ProductAdapter

        val mainViewModel by viewModel<MainViewModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            installSplashScreen().apply {
                setKeepOnScreenCondition {

                    mainViewModel.isLoading.value ?: true
                }
            }
            setContentView(R.layout.activity_main)
            setupRecyclerView()
            observeViewModel()

            mainViewModel.loadProducts()
        }

        private fun setupRecyclerView() {
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        mainViewModel.loadProducts()
                    }
                }
            })

            productAdapter = ProductAdapter(listOf())
            recyclerView.adapter = productAdapter
        }

        private fun observeViewModel() {
            mainViewModel.products.observe(this) { products ->
                productAdapter.updateData(products)
            }
        }

}