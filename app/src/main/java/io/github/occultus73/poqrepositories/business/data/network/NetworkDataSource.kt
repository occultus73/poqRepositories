package com.codingwithmitch.daggerhiltplayground.business.data.network

import androidx.lifecycle.LiveData
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem


interface NetworkDataSource {

    suspend fun get(): List<SquareReposItem>
}