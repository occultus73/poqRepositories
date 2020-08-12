package io.github.occultus73.poqrepositories.framework.presentation.state

import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem

data class MainViewState(val squareReposItems: List<SquareReposItem> = listOf())