package com.rakapermanaptr.domain.home.repository

import com.rakapermanaptr.domain.home.entity.PokemonList

interface IHomeRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonList
}