package com.devmike.gamepedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
