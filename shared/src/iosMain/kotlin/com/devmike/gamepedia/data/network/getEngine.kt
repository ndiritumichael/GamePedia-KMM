package com.devmike.gamepedia.data.network

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun getEngine(): HttpClientEngine {
   return Darwin.create()
}