package com.devmike.gamepedia.data.network

import com.devmike.gamepedia.Utils.API_KEY
import com.devmike.gamepedia.data.network.datasources.models.gamedetails.GameDetailsResponse
import com.devmike.gamepedia.data.network.models.gamesresponse.GamesResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
class GameApiService(private val client: HttpClient) {
    suspend fun getGames(page: Int = 1, pageSize: Int = 10, searchString: String? = null): GamesResponse {
        Napier.e {
            "The log is $client"
        }
        return client.get("/api/games") {
            parameter("page", page)
            parameter("key", API_KEY)
            parameter("page_size", pageSize)
            searchString?.let {
                parameter("search",it)
            }
        }.body()
    }

    suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return client.get("games") {
        }.body()
    }
}
