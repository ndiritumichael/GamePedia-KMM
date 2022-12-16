package com.devmike.gamepedia.data.mappers

import com.devmike.gamepedia.Utils.Constants
import com.devmike.gamepedia.Utils.getRatingColor
import com.devmike.gamepedia.data.network.datasources.models.gamesresponse.GamesDTO
import com.devmike.gamepedia.domain.models.Game
import com.devmike.gamepedia.domain.models.ScoreRating

fun GamesDTO.toDomainGame(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage ?: Constants.DEFAULTIMAGE,
        dominantColor = saturatedColor,
        scoreRating = metacritic?.let { ScoreRating(metacritic, getRatingColor(metacritic)) },
        rating = rating,
        platforms = parentPlatforms.map { it.platform.slug }
    )
}
