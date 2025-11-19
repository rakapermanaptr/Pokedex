package com.rakapermanaptr.domain.home.di

import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import org.koin.dsl.module

val homeUseCaseModule = module {
    factory { GetPokemonListUseCase(get()) }
}