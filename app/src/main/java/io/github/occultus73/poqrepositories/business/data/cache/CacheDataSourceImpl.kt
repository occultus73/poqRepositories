package com.codingwithmitch.daggerhiltplayground.business.data.cache

import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem

class CacheDataSourceImpl: CacheDataSource {
    override suspend fun insertList(list: List<SquareReposItem>) { }

    override suspend fun get(): List<SquareReposItem> {
        return listOf()
    }
}
