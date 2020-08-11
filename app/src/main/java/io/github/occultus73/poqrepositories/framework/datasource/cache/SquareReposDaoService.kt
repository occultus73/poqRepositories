package io.github.occultus73.poqrepositories.framework.datasource.cache

import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity

interface SquareReposDaoService {

    suspend fun insert(squareReposEntity: SquareReposCacheEntity): Long

    suspend fun get(): List<SquareReposCacheEntity>

}