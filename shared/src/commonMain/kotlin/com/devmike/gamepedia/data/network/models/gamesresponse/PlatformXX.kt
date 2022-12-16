package com.devmike.gamepedia.data.network.datasources.models.gamesresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformXX(
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String?,
    @SerialName("image_background")
    val imageBackground: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("year_end")
    val yearEnd: String?,
    @SerialName("year_start")
    val yearStart: Int?
)
