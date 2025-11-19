package com.rakapermanaptr.presentation.di

import com.rakapermanaptr.presentation.home.di.homeViewModelModule
import org.koin.core.module.Module

val viewModelModules: List<Module>
    get() = listOf(
        homeViewModelModule,
    )