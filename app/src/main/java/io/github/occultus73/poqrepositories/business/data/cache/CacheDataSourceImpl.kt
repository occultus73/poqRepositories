package io.github.occultus73.poqrepositories.business.data.cache

import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.framework.datasource.cache.SquareReposDaoService
import io.github.occultus73.poqrepositories.framework.datasource.cache.mappers.CacheMapper

class CacheDataSourceImpl
constructor(
    private val squareReposDaoService: SquareReposDaoService,
    private val cacheMapper: CacheMapper
): CacheDataSource {

    override suspend fun insert(squareReposItem: SquareReposItem): Long {
        return squareReposDaoService.insert(cacheMapper.mapToEntity(squareReposItem))
    }

    override suspend fun insertList(squareReposItems: List<SquareReposItem>) {
        for(squareReposItem in squareReposItems){
            squareReposDaoService.insert(cacheMapper.mapToEntity(squareReposItem))
        }
    }

    override suspend fun get(): List<SquareReposItem> {
        return cacheMapper.mapFromEntityList(squareReposDaoService.get())
    }
}
