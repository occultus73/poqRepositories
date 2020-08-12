package io.github.occultus73.poqrepositories.framework.datasource.cache

import io.github.occultus73.poqrepositories.framework.datasource.cache.database.SquareReposDao
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SquareReposDaoServiceImpl
constructor(
    private val squareReposDao: SquareReposDao
) : SquareReposDaoService {

    override suspend fun insert(squareReposEntity: SquareReposCacheEntity): Long {
        return withContext(Dispatchers.IO){ squareReposDao.insert(squareReposEntity) }
    }

    override suspend fun get(): List<SquareReposCacheEntity> {
        return withContext(Dispatchers.IO){ squareReposDao.get() }
    }
}