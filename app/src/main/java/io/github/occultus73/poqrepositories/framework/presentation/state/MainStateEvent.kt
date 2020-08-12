package io.github.occultus73.poqrepositories.framework.presentation.state

sealed class MainStateEvent {
    object GetSquareReposEvent : MainStateEvent()
    object None : MainStateEvent()
}