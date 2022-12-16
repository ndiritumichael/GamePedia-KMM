package com.devmike.gamepedia.di

import com.devmike.gamepedia.data.datasources.GamesRepositoryImpl
import com.devmike.gamepedia.data.network.GameApiService
import com.devmike.gamepedia.data.network.gameClient
import com.devmike.gamepedia.data.network.getEngine
import com.devmike.gamepedia.domain.repositories.GamesRepository
import com.devmike.gamepedia.getPlatform
import com.devmike.gamepedia.presenters.GamesPresenters
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {
    single {
        gameClient(enableNetworkLogs, get())
    }
    single {
        getPlatform()
    }

    single {
        GameApiService(get())
    }

    factory {
        GamesPresenters()
    }
    single {
        getEngine()
    }

    single<GamesRepository> {
        GamesRepositoryImpl(get())
    }
}
