package com.devmike.gamepedia.di


import com.devmike.gamepedia.presenters.GamesPresenters
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules( commonModule(enableNetworkLogs))
    }

// called by iOS etc
fun initiOSKoin(debug : Boolean) = initKoin(enableNetworkLogs = debug) {}

fun KoinApplication.Companion.start(enableNetworkLogs: Boolean): KoinApplication = initKoin(enableNetworkLogs = enableNetworkLogs) { }

val Koin.gamePresenter : GamesPresenters
get() = get()



fun debugBuild() {
    Napier.base(DebugAntilog())
}
