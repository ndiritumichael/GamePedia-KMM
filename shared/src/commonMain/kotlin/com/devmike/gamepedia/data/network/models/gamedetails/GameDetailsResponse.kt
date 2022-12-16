package com.devmike.gamepedia.data.network.datasources.models.gamedetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsResponse(
    @SerialName("achievements_count")
    val achievementsCount: Int,
    @SerialName("added")
    val added: Int,
    @SerialName("added_by_status")
    val addedByStatus: AddedByStatus,
    @SerialName("additions_count")
    val additionsCount: Int,
    @SerialName("alternative_names")
    val alternativeNames: List<String>,
    @SerialName("background_image")
    val backgroundImage: String,
    @SerialName("background_image_additional")
    val backgroundImageAdditional: String,
    @SerialName("creators_count")
    val creatorsCount: Int,
    @SerialName("description")
    val description: String,
    @SerialName("esrb_rating")
    val esrbRating: EsrbRating,
    @SerialName("game_series_count")
    val gameSeriesCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("metacritic")
    val metacritic: Int,
    @SerialName("metacritic_platforms")
    val metacriticPlatforms: List<MetacriticPlatform>,
    @SerialName("metacritic_url")
    val metacriticUrl: String,
    @SerialName("movies_count")
    val moviesCount: Int,
    @SerialName("name")
    val name: String,
    @SerialName("name_original")
    val nameOriginal: String,
    @SerialName("parent_achievements_count")
    val parentAchievementsCount: String,
    @SerialName("parents_count")
    val parentsCount: Int,
    @SerialName("platforms")
    val platforms: List<Platform>,
    @SerialName("playtime")
    val playtime: Int,
    @SerialName("rating")
    val rating: Int,
    @SerialName("rating_top")
    val ratingTop: Int,
    @SerialName("ratings")
    val ratings: Ratings,
    @SerialName("ratings_count")
    val ratingsCount: Int,
    @SerialName("reactions")
    val reactions: Reactions,
    @SerialName("reddit_count")
    val redditCount: Int,
    @SerialName("reddit_description")
    val redditDescription: String,
    @SerialName("reddit_logo")
    val redditLogo: String,
    @SerialName("reddit_name")
    val redditName: String,
    @SerialName("reddit_url")
    val redditUrl: String,
    @SerialName("released")
    val released: String,
    @SerialName("reviews_text_count")
    val reviewsTextCount: String,
    @SerialName("screenshots_count")
    val screenshotsCount: Int,
    @SerialName("slug")
    val slug: String,
    @SerialName("suggestions_count")
    val suggestionsCount: Int,
    @SerialName("tba")
    val tba: Boolean,
    @SerialName("twitch_count")
    val twitchCount: String,
    @SerialName("updated")
    val updated: String,
    @SerialName("website")
    val website: String,
    @SerialName("youtube_count")
    val youtubeCount: String
)
