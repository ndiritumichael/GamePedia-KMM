package com.devmike.gamepedia.data.network.datasources.models.gamedetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    @SerialName("platform")
    val platform: PlatformX,
    @SerialName("released_at")
    val releasedAt: String,
    @SerialName("requirements")
    val requirements: Requirements
)