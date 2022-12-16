package com.devmike.gamepedia.domain.models

data class Game(
    val id: Int,
    val name: String,
    val backgroundImage: String,
    val dominantColor: String,
    val scoreRating: ScoreRating?,
    val rating: Double,
    val platforms: List<String>

)
data class ScoreRating(
    val metaCriticScore: Int,
    val color: Long
)
