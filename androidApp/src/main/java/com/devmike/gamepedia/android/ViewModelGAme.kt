package com.devmike.gamepedia.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.devmike.gamepedia.domain.models.Game
import com.devmike.gamepedia.presenters.GamesPresenters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ViewModelGAme(private val presenters: GamesPresenters) : ViewModel() {
    var data: Flow<PagingData<Game>> = flowOf()

    init {
        viewModelScope.launch {

//            presenters.gameFlow.collect{
//                data = flowOf(it as PagingData<Game>)
//
//
//            }
        }
    }
}
