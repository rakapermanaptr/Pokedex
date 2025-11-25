package com.rakapermanaptr.presentation.home

import androidx.lifecycle.viewModelScope
import com.rakapermanaptr.base.BaseViewModel
import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) :
    BaseViewModel<HomeViewEvent, HomeViewState, HomeViewEffect>(
        initialState = HomeViewState()
    ) {

    override suspend fun handleEvent(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.Initial -> getPokemonList()
            HomeViewEvent.LoadPokemons -> getPokemonList()
        }
    }

    private var currentOffset = 0
    private var pageSize = 20

    private fun getPokemonList() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            runCatching {
                getPokemonListUseCase(currentOffset, pageSize)
            }.onSuccess { result ->
                setState {
                    copy(
                        isLoading = false,
                        hasNextPage = result.nextOffset != null,
                        pokemonList = pokemonList + result.pokemonList
                    )
                }

                currentOffset = result.nextOffset ?: currentOffset
            }.onFailure {
                setState { copy(isLoading = false) }
                setEffect { HomeViewEffect.ShowErrorMessage(it.message.orEmpty()) }
            }
        }
    }
}