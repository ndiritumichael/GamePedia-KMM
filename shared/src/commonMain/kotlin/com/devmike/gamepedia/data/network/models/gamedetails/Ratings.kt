package com.devmike.gamepedia.data.network.datasources.models.gamedetails

import kotlinx.serialization.Serializable

@Serializable
data class Ratings(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Float
)
