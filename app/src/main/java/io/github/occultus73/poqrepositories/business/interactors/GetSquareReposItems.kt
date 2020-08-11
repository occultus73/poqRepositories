package io.github.occultus73.poqrepositories.business.interactors

import com.codingwithmitch.daggerhiltplayground.business.data.cache.CacheDataSource
import com.codingwithmitch.daggerhiltplayground.business.data.network.NetworkDataSource
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSquareReposItems
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
){
    suspend fun execute(): Flow<DataState<List<SquareReposItem>>> = flow {
        emit(DataState.Loading)
        val networkSquareReposItem = networkDataSource.get()
        cacheDataSource.insertList(networkSquareReposItem)
        val cachedSquareReposItem = cacheDataSource.get()
        emit(DataState.Success(cachedSquareReposItem))
    }
}