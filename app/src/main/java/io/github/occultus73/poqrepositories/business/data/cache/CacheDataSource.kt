package io.github.occultus73.poqrepositories.business.data.cache

import androidx.lifecycle.LiveData
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem

interface CacheDataSource {

    suspend fun insert(squareReposItem: SquareReposItem): Long

    suspend fun insertList(squareReposItems: List<SquareReposItem>)

    suspend fun get(): List<SquareReposItem>
}