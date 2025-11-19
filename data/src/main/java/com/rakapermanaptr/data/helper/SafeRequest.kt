package com.rakapermanaptr.data.helper

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import java.io.IOException

suspend inline fun <reified T> safeRequest(
    client: HttpClient,
    url: String,
    method: HttpMethod = HttpMethod.Get,
    noinline block: (HttpRequestBuilder.() -> Unit)? = null
): T {
    val response = try {
        client.request(url) {
            this.method = method
            contentType(ContentType.Application.Json)
            block?.let { this.apply(it) }
        }
    } catch (e: Exception) {
        throw Exception("unknown error")
    }

    if (response.status.isSuccess()) {
        return try {
            response.body<T>()
        } catch (e: Exception) {
            throw Exception("failed to parse API response")
        }
    } else {
        val status = response.status
        val errorMessage = "request failed. status: $status"

        throw Exception(errorMessage)
    }
}