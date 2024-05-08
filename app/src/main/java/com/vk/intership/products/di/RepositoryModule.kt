package com.vk.intership.products.di

import com.vk.intership.products.data.ProductRepository
import com.vk.intership.products.network.DataSource
import com.vk.intership.products.network.DataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ProductRepository(get()) }
}

val networkModule = module {
    single<DataSource> { DataSourceImpl() }
}