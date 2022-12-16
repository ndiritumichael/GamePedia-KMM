package com.devmike.gamepedia.data.network.datasources.models.gamesresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformX(
//    @SerialName("platform")
//    val platform: PlatformXX? = null,
    @SerialName("released_at")
    val releasedAt: String? = null,
    @SerialName("requirements_en")
    val requirementsEn: RequirementsEn? = null,
    @SerialName("requirements_ru")
    val requirementsRu: RequirementsRu? = null
)