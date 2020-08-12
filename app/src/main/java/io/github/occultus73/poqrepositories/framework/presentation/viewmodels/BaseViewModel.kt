package io.github.occultus73.poqrepositories.framework.presentation.viewmodels

import androidx.lifecycle.*
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import io.github.occultus73.poqrepositories.framework.presentation.state.MainViewState
import kotlinx.coroutines.launch


abstract class BaseViewModel<StateEvent, ViewState> : ViewModel() {
    protected val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    protected val _dataState: MutableLiveData<DataState<ViewState>> = MutableLiveData()

    protected abstract suspend fun handleStateEvent(stateEvent: StateEvent)

    protected abstract fun initNewViewState(): ViewState

    fun setStateEvent(event: StateEvent) = viewModelScope.launch { handleStateEvent(event) }

    fun getCurrentViewState(): ViewState {
        return viewState.value ?: initNewViewState()
    }

    fun setViewState(mainViewState: ViewState){
        if(mainViewState != _viewState.value){
            _viewState.value = mainViewState
        }
    }

    val viewState: LiveData<ViewState>
        get() = _viewState

    val dataState: LiveData<DataState<ViewState>>
        get() = _dataState

}