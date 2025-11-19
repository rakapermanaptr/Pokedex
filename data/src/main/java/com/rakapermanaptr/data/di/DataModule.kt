package com.rakapermanaptr.data.di

import com.rakapermanaptr.data.home.di.homeDataModule
import org.koin.core.module.Module

val dataModules: List<Module>
    get() = listOf(
        homeDataModule
    )