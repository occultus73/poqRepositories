package io.github.occultus73.poqrepositories.framework.datasource.network

import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposNetworkEntity

interface SquareReposRetrofitService {
    suspend fun get(): List<SquareReposNetworkEntity>
}