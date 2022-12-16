package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image_background")
    val imageBackground: String?,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String
)