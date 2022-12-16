package com.devmike.gamepedia.data.network.datasources.models.gamedetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetacriticPlatform(
    @SerialName("metascore")
    val metascore: Int,
    @SerialName("url")
    val url: String
)