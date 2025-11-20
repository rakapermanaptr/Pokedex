package com.rakapermanaptr.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakapermanaptr.domain.home.entity.Pokemon
import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonListUseCase: GetPokemonListUseCase): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state.asStateFlow()

    private var currentOffset = 0
    private var pageSize = 20

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            runCatching {
                getPokemonListUseCase(currentOffset, pageSize)
            }.onSuccess { pokemonList ->
                _state.update {
                    it.copy(
                        pokemonList = it.pokemonList + pokemonList.pokemonList,
                        hasNextPage = pokemonList.nextOffset != null,
                        isLoading = false
                    )
                }

                currentOffset = pokemonList.nextOffset ?: currentOffset
            }.onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val hasNextPage: Boolean = true
)