package com.rakapermanaptr.domain.home.entity

data class PokemonList(
    val pokemonList: List<Pokemon>,
    val nextOffset: Int?
)
