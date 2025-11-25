package com.rakapermanaptr.presentation.home

import com.rakapermanaptr.base.UiEffect
import com.rakapermanaptr.base.UiEvent
import com.rakapermanaptr.domain.home.entity.Pokemon

sealed class HomeViewEvent: UiEvent {
    data object Initial: HomeViewEvent()
    data object LoadPokemons: HomeViewEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val hasNextPage: Boolean = true,
    val pokemonList: List<Pokemon> = emptyList()
)

sealed class HomeViewEffect : UiEffect {
    data class ShowErrorMessage(val errMsg: String): HomeViewEffect()
}

