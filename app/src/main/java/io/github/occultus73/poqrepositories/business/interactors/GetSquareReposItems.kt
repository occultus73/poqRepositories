package io.github.occultus73.poqrepositories.business.interactors

import io.github.occultus73.poqrepositories.business.data.cache.CacheDataSource
import io.github.occultus73.poqrepositories.business.data.network.NetworkDataSource
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import io.github.occultus73.poqrepositories.framework.presentation.state.MainViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSquareReposItems
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
){
    suspend fun execute(): Flow<DataState<MainViewState>> = flow {
        emit(DataState.Loading)

        try {
            val networkSquareReposItem = networkDataSource.get()
            cacheDataSource.insertList(networkSquareReposItem)
            val cachedSquareReposItem = cacheDataSource.get()
            val mainViewState = MainViewState(cachedSquareReposItem)

            emit(DataState.Success(mainViewState))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}