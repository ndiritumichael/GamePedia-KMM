package com.devmike.gamepedia.data.network

import com.devmike.gamepedia.Utils.API_KEY
import com.devmike.gamepedia.Utils.Constants
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.serialization.json.Json

fun gameClient(installLogging: Boolean,engine: HttpClientEngine) = HttpClient (engine){
    defaultRequest {
        url {
           host = Constants.RAWG_BASE_URL
            protocol = URLProtocol.HTTPS
            parameters.append("key", API_KEY)
            // headers.appendIfNameAbsent(HttpHeaders.ContentType, ContentType.Application.Json.contentType)
        }
    }
    if (installLogging) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.e { " the ktor log is $message" }
                }
            }
            level = LogLevel.BODY
        }
    }

    install(ContentNegotiation){
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
                encodeDefaults = true
            }
        )
    }
}
