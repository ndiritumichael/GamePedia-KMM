package com.devmike.gamepedia


import io.github.aakira.napier.Napier
import platform.Foundation.NSURLSessionResponseAllow
import platform.UIKit.UIDevice

class IOSPlatform : Platform {



 override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform().also {
    Napier.d { "Hello from ios, ${it.name}!" }
}
