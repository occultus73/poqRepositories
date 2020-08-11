package com.codingwithmitch.daggerhiltplayground.business.data.cache

import androidx.lifecycle.LiveData
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem

interface CacheDataSource {

    suspend fun insertList(list: List<SquareReposItem>)

    suspend fun get(): List<SquareReposItem>
}