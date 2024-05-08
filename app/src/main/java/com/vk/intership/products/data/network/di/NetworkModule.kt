package com.vk.intership.products.data.network.di

import com.vk.intership.products.data.network.DataSource
import com.vk.intership.products.data.network.DataSourceImpl
import org.koin.dsl.module

val networkModule = module {
    single<DataSource> { DataSourceImpl() }
}