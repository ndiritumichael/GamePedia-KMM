package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesDTO(
    @SerialName("added")
    val added: Int,
//    @SerialName("added_by_status")
//    val addedByStatus: AddedByStatus?,
    @SerialName("background_image")
    val backgroundImage: String?,
    @SerialName("clip")
    val clip: String?,
    @SerialName("dominant_color")
    val dominantColor: String,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("id")
    val id: Int,
    @SerialName("metacritic")
    val metacritic: Int?,
    @SerialName("name")
    val name: String,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>,
    @SerialName("platforms")
    val platforms: List<PlatformX>,
    @SerialName("playtime")
    val playtime: Int,
    @SerialName("rating")
    val rating: Double,
    @SerialName("rating_top")
    val ratingTop: Int,
    @SerialName("ratings")
    val ratings: List<Rating>,
    @SerialName("ratings_count")
    val ratingsCount: Int,
    @SerialName("released")
    val released: String?,
    @SerialName("reviews_count")
    val reviewsCount: Int,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerialName("saturated_color")
    val saturatedColor: String,
    @SerialName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>? = emptyList(),
    @SerialName("slug")
    val slug: String,
//    @SerialName("stores")
//    val stores: List<Store>,
    @SerialName("suggestions_count")
    val suggestionsCount: Int,
    @SerialName("tags")
    val tags: List<Tag>? = emptyList(),
    @SerialName("tba")
    val tba: Boolean,
    @SerialName("updated")
    val updated: String,
    @SerialName("user_game")
    val userGame: String?
)