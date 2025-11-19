package com.rakapermanaptr.domain.di

import com.rakapermanaptr.domain.home.di.homeUseCaseModule
import org.koin.core.module.Module

val useCaseModules: List<Module>
    get() = listOf(
        homeUseCaseModule
    )