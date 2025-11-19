package com.rakapermanaptr.data.home.response


import com.rakapermanaptr.data.home.response.Result
import com.rakapermanaptr.domain.home.entity.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

fun Result.toDomain() = Pokemon(
    name = name.orEmpty()
)