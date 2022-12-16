package com.devmike.gamepedia.domain.repositories

import com.devmike.gamepedia.domain.models.Game

interface GamesRepository{
    suspend fun getGames(): List<Game>

}