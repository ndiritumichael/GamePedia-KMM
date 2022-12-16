package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequirementsRu(
    @SerialName("minimum")
    val minimum: String,
    @SerialName("recommended")
    val recommended: String
)