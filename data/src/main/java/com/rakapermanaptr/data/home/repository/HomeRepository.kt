package com.rakapermanaptr.data.home.repository

import com.rakapermanaptr.data.home.api.IHomeApi
import com.rakapermanaptr.data.home.response.toDomain
import com.rakapermanaptr.domain.home.entity.PokemonList
import com.rakapermanaptr.domain.home.repository.IHomeRepository

class HomeRepository(private val api: IHomeApi) : IHomeRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonList {
        val apiResponse = api.getPokemonList(offset, limit)

        val nextOffset = if (apiResponse.next != null) {
            offset + limit
        } else null

        return PokemonList(
            pokemonList = apiResponse.results.orEmpty().map { it.toDomain() },
            nextOffset = nextOffset
        )
    }

}