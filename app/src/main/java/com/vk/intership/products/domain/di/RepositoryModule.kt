package com.vk.intership.products.domain.di

import com.vk.intership.products.domain.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ProductRepository(get()) }
}

