package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("count")
    val count: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("percent")
    val percent: Double,
    @SerialName("title")
    val title: String
)
