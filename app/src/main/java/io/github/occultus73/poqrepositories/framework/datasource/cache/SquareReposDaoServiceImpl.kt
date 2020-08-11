package io.github.occultus73.poqrepositories.framework.datasource.cache

import io.github.occultus73.poqrepositories.framework.datasource.cache.database.SquareReposDao
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity

class SquareReposDaoServiceImpl
constructor(
    private val squareReposDao: SquareReposDao
) : SquareReposDaoService {

    override suspend fun insert(squareReposEntity: SquareReposCacheEntity): Long {
        return squareReposDao.insert(squareReposEntity)
    }

    override suspend fun get(): List<SquareReposCacheEntity> {
        return squareReposDao.get()
    }
}