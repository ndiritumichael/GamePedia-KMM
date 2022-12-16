package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("games_count")
    val gamesCount: Int = 0,
    @SerialName("id")
    val id: Int,
    @SerialName("image_background")
    val imageBackground: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null
)