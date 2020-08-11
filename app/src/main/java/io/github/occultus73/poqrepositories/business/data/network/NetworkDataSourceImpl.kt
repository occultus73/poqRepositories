package com.codingwithmitch.daggerhiltplayground.business.data.network

import androidx.lifecycle.LiveData
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.framework.datasource.network.SquareReposRetrofitService
import io.github.occultus73.poqrepositories.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl(
    private val squareReposRetrofitService: SquareReposRetrofitService,
    private val networkMapper: NetworkMapper
) : NetworkDataSource  {

    override suspend fun get(): List<SquareReposItem> {
        return networkMapper.mapFromEntityList(squareReposRetrofitService.get())
    }

}