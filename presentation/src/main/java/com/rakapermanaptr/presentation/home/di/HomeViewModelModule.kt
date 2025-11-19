package com.rakapermanaptr.presentation.home.di

import com.rakapermanaptr.presentation.home.HomeViewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    factory { HomeViewModel(get()) }
}