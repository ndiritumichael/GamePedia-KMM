package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import com.devmike.gamepedia.data.network.models.gamesresponse.Platform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentPlatform(
    @SerialName("platform")
    val platform: Platform
)