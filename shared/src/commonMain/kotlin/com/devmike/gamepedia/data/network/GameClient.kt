package com.devmike.gamepedia.data.network

import io.ktor.client.*
import io.ktor.client.plugins.logging.*

fun client(installLogging: Boolean) = HttpClient {
    if (installLogging) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }
}
