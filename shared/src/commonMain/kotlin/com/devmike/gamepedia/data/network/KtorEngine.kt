package com.devmike.gamepedia.data.network

import io.ktor.client.engine.*

expect fun getEngine(): HttpClientEngine
