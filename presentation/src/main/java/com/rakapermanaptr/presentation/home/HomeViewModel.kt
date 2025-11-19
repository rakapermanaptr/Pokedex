package com.rakapermanaptr.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonListUseCase: GetPokemonListUseCase): ViewModel() {

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            runCatching {
                getPokemonListUseCase(offset = 25, limit = 20)
            }.onSuccess {
                println("TEST_DEBUG: $it")
            }.onFailure {

            }
        }
    }
}