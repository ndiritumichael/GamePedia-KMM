package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequirementsEn(
    @SerialName("minimum")
    val minimum: String? = null,
    @SerialName("recommended")
    val recommended: String? = null
)
