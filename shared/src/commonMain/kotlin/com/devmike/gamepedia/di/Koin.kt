package com.devmike.gamepedia.di

import com.devmike.gamepedia.getPlatform
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModules())
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }

fun commonModules() = org.koin.dsl.module {
    single {
        getPlatform()
    }
}

fun debugBuild() {
    Napier.base(DebugAntilog())
}
