package com.devmike.gamepedia.presenters

import com.devmike.gamepedia.Utils.asCommonFlow
import com.devmike.gamepedia.Utils.getQueryKey
import com.devmike.gamepedia.data.network.GameApiService
import com.devmike.gamepedia.data.mappers.toDomainGame
import com.devmike.gamepedia.domain.models.Game
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import io.github.aakira.napier.Napier
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GamesPresenters : KoinComponent {
    private val api: GameApiService by inject()

    private var gamesJob: Job? = null

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val defaultGames = MyMultiplatformController(api = api, scope = viewModelScope)

    private val _gamesData = MutableStateFlow(defaultGames)
    val gamesData
        get() = _gamesData.asStateFlow()

    val gamesDataValue
        get() = gamesData.value

    val iosGamesData
        get() = gamesData.asCommonFlow()

    fun searchGames(text: String) {
        if (text.length < 3) {
            _gamesData.value = defaultGames
        } else {
            gamesJob?.cancel()
            gamesJob = viewModelScope.launch {
                delay(500)
         _gamesData.value = MyMultiplatformController(searchString = text, api = api, viewModelScope)
            }
        }
    }
}

class MyMultiplatformController(private val searchString: String? = null, private val api: GameApiService, val scope: CoroutineScope = MainScope()) {

    private val search = searchString?.let {
        it.ifEmpty { null }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pager = Pager(
        clientScope = scope,
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false // Ignored on iOS
        ),
        initialKey = 1, // Key to use when initialized
        getItems = { currentKey, size ->
            val gamesResponse = api.getGames(page = currentKey, pageSize = size, searchString = search)
            val items = gamesResponse.results.map { it.toDomainGame() }

            PagingResult(
                items = items,
                currentKey = currentKey,
                prevKey = { gamesResponse.previous?.getQueryKey() }, // Key for previous page, null means don't load previous pages
                nextKey = {
                    gamesResponse.next?.getQueryKey()
                } // Key for next page. Use `items` or `currentKey` to get it depending on the pagination strategy
            )
        }
    )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagingData: Flow<PagingData<Game>>
        get() = pager.pagingData
            .cachedIn(scope)
    // cachedIn from AndroidX Paging. on iOS, this is a no-op

    val pagingDataIos
        get() = pagingData.asCommonFlow()

    init {
        Napier.e("The class has been recreated again")
    }

    // So that iOS can consume the Flow
}

/**
 * Tried using the cashapp paging library which is still having issues
 * resorting to using kuuuurt multiplatform-paging for now
 * https://github.com/kuuuurt/multiplatform-paging
*/
/* class GamesPagingSource(
    private val name: String? = null,
    private val api : GameApiService
):PagingSource<Int,Game>(){


     companion object {

         const val FIRST_PAGE_INDEX = 1
     }

//     override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
//         return null
//     }

     override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Game> {
         val key = params.key ?: FIRST_PAGE_INDEX
         return try {
             val gamesResponse = api.getGames(page = key)
             val results = gamesResponse.results.map { it.toDomainGame() }

             val nextPage = gamesResponse.next.getQueryKey()
             val previousPage  = gamesResponse.previous?.getQueryKey()

             PagingSourceLoadResultPage(
                 data = results,
                 prevKey = previousPage,
                 nextKey = nextPage

             ) as PagingSourceLoadResult<Int, Game>

         } catch (e:Exception){
             PagingSourceLoadResultError<Int,Game>(e) as PagingSourceLoadResult<Int, Game>
         }
     }

     override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
         TODO("Not yet implemented")
     }
 }*/
