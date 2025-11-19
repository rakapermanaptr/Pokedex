package com.rakapermanaptr.domain.home.usecase

import com.rakapermanaptr.domain.home.repository.IHomeRepository

class GetPokemonListUseCase(private val repository: IHomeRepository) {

    suspend operator fun invoke(offset: Int, limit: Int) = repository.getPokemonList(offset, limit)
}