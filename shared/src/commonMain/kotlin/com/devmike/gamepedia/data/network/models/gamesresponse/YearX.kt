package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YearX(
    @SerialName("count")
    val count: Int,
    @SerialName("nofollow")
    val nofollow: Boolean,
    @SerialName("year")
    val year: Int
)
