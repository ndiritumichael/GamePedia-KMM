package com.devmike.gamepedia

import io.github.aakira.napier.Napier
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
    private val platform: Platform by inject()

    fun greeting(): String {
        Napier.d { "Hello, ${platform.name}!" }
        return "Hello, ${platform.name}!"
    }
}
