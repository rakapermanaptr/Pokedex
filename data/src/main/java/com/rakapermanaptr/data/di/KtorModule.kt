package com.rakapermanaptr.data.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = CustomLogger()
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                url("https://pokeapi.co/api/v2/")
            }
        }
    }
}

class CustomLogger : Logger {
    override fun log(message: String) {
        println("HttpLogger: $message")
    }
}
