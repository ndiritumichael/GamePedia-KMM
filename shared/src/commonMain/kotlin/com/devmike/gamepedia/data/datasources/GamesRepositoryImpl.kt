package com.devmike.gamepedia.data.datasources

import com.devmike.gamepedia.data.mappers.toDomainGame
import com.devmike.gamepedia.data.network.GameApiService
import com.devmike.gamepedia.domain.models.Game
import com.devmike.gamepedia.domain.repositories.GamesRepository

internal class GamesRepositoryImpl(private val apiService: GameApiService) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return apiService.getGames().results.map { it.toDomainGame() }
    }
}
