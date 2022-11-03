package com.devmike.gamepedia

import io.github.aakira.napier.Napier

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform().also {
    Napier.d { "Hello from ios, ${it.name}!" }
}
