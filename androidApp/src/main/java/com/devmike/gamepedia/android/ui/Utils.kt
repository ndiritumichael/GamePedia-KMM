package com.devmike.gamepedia.android.ui

import androidx.compose.ui.graphics.Color
import com.devmike.gamepedia.android.R.drawable

fun getRatingColor(score: Int): Color {
    return if (score > 80) {
        Color.Green
    } else if (score > 40) {
        Orange
    } else if (score > 0) {
        Color.Red
    } else Color.Gray
}

fun getPlatformLogo(name: String): Int {
    return when (name) {
        "pc" -> drawable.windows_10logo
        "playstation" -> drawable.pslogo
        "xbox" -> drawable.xboxlogo
        "nintendo" -> drawable.nintendologo
        "android" -> drawable.androidlogo
        "mac" -> drawable.applelogo
        "linux" -> drawable.linuxlogo
        else -> drawable.browser
    }
}
