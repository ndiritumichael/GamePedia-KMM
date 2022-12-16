package com.devmike.gamepedia.data.network.models.gamesresponse


import com.devmike.gamepedia.data.network.datasources.models.gamesresponse.Filters
import com.devmike.gamepedia.data.network.datasources.models.gamesresponse.GamesDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("description")
    val description: String? = null,
    @SerialName("filters")
    val filters: Filters? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("nofollow")
    val nofollow: Boolean = false,
    @SerialName("nofollow_collections")
    val nofollowCollections: List<String> = listOf(),
    @SerialName("noindex")
    val noindex: Boolean = false,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<GamesDTO>,
    @SerialName("seo_description")
    val seoDescription: String? = null,
    @SerialName("seo_h1")
    val seoH1: String? = null,
    @SerialName("seo_keywords")
    val seoKeywords: String? = null,
    @SerialName("seo_title")
    val seoTitle: String? = null,
)