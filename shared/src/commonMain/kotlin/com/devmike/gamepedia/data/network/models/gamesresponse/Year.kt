package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Year(
    @SerialName("count")
    val count: Int,
    @SerialName("decade")
    val decade: Int,
    @SerialName("filter")
    val filter: String,
    @SerialName("from")
    val from: Int,
    @SerialName("nofollow")
    val nofollow: Boolean,
    @SerialName("to")
    val to: Int,
    @SerialName("years")
    val years: List<YearX>
)