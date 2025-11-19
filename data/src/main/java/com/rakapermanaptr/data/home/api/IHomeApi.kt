package com.rakapermanaptr.data.home.api

import com.rakapermanaptr.data.home.response.PokemonListResponse

interface IHomeApi {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse
}