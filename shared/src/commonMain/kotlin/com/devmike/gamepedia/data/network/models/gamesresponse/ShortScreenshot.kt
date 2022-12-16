package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShortScreenshot(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String
)
