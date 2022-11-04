package com.devmike.gamepedia

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
    private val platform: Platform by inject()

    fun greeting(): String {
        Napier.d { "Hello, ${platform.name}!" }

        return "Hello, ${platform.name}!"
    }
}

class GreetingKtor {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.body()
    }
}
