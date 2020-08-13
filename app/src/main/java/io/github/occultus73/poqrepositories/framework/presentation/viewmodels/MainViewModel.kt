package io.github.occultus73.poqrepositories.framework.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import io.github.occultus73.poqrepositories.business.interactors.GetSquareReposItems
import io.github.occultus73.poqrepositories.framework.presentation.state.MainStateEvent
import io.github.occultus73.poqrepositories.framework.presentation.state.MainStateEvent.GetSquareReposEvent
import io.github.occultus73.poqrepositories.framework.presentation.state.MainViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    //@Assisted private val savedStateHandle: SavedStateHandle,
    private val getSquareReposItems: GetSquareReposItems
) : BaseViewModel<MainStateEvent, MainViewState>() {
    override suspend fun handleStateEvent(stateEvent: MainStateEvent) {
        when (stateEvent) {
            is GetSquareReposEvent -> getSquareReposItems.execute().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)

            is MainStateEvent.None -> {}
        }
    }

    override fun initNewViewState() = MainViewState()
}