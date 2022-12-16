package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filters(
    @SerialName("years")
    val years: List<Year>
)
