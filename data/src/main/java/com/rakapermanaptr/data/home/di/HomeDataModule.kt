package com.rakapermanaptr.data.home.di

import com.rakapermanaptr.data.home.api.HomeApi
import com.rakapermanaptr.data.home.api.IHomeApi
import com.rakapermanaptr.data.home.repository.HomeRepository
import com.rakapermanaptr.domain.home.repository.IHomeRepository
import org.koin.dsl.module

val homeDataModule = module {
    single<IHomeApi> { HomeApi(get()) }
    single<IHomeRepository> { HomeRepository(get()) }
}