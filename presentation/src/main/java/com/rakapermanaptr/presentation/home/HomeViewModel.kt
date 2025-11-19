package com.rakapermanaptr.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakapermanaptr.domain.home.entity.Pokemon
import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonListUseCase: GetPokemonListUseCase): ViewModel() {

    init {
        getPokemonList()
    }

    private val _state = MutableStateFlow<List<Pokemon>>(listOf())
    val state: StateFlow<List<Pokemon>> = _state.asStateFlow()

    private fun getPokemonList() {
        viewModelScope.launch {
            runCatching {
                getPokemonListUseCase(offset = 25, limit = 20)
            }.onSuccess {
                _state.value = it.pokemonList
                println("TEST_DEBUG: $it")
            }.onFailure {

            }
        }
    }
}