package io.github.occultus73.poqrepositories.framework.datasource.network

import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposNetworkEntity
import io.github.occultus73.poqrepositories.framework.datasource.network.retrofit.SquareReposRetrofit

class SquareReposRetrofitServiceImpl
constructor(
    private val squareReposRetrofit: SquareReposRetrofit
) : SquareReposRetrofitService {
    override suspend fun get(): List<SquareReposNetworkEntity> {
        return squareReposRetrofit.get()
    }
}