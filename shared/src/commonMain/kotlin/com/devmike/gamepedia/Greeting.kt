package com.devmike.gamepedia

import com.devmike.gamepedia.data.network.GameApiService
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
    private val platform: Platform by inject()

    fun greeting(): String {
        Napier.d { "Hello, ${platform.name}!" }

        return "Hello, ${platform.name}!"
    }
}

class GreetingKtor : KoinComponent {
    private val apiService: GameApiService by inject()
    private val client = HttpClient() {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.e { " the ktor log is $message" }
                }
            }
            level = LogLevel.BODY
        }
    }

    suspend fun greeting111(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.body()
    }

    suspend fun getGames(): String {
        return try { apiService.getGames(10, 10).toString()} catch (e : Exception){
            Napier.e { e.stackTraceToString() }


            "Something went wrong "
        }
    }
}
