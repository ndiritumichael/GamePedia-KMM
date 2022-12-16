package com.devmike.gamepedia.data.network.datasources.models.gamedetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Requirements(
    @SerialName("minimum")
    val minimum: String,
    @SerialName("recommended")
    val recommended: String
)