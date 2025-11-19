package com.rakapermanaptr.data.home.api

import com.rakapermanaptr.data.helper.safeRequest
import com.rakapermanaptr.data.home.response.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod

class HomeApi(private val httpClient: HttpClient) : IHomeApi {
    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse {
        return safeRequest<PokemonListResponse>(
            client = httpClient,
            url = "pokemon",
            method = HttpMethod.Get,
        ) {
            parameter("offset", offset)
            parameter("limit", limit)
        }
    }

}