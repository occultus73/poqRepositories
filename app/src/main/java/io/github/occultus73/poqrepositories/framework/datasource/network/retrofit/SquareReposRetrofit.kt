package io.github.occultus73.poqrepositories.framework.datasource.network.retrofit

import androidx.lifecycle.LiveData
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposNetworkEntity
import retrofit2.http.*

interface SquareReposRetrofit {

    //Documentation: https://developer.github.com/v3/repos/#list-organization-repositories

    @GET("orgs/{org}/repos")
    suspend fun get(
        @Path("org") org: String = "square",
        @Query("type") type: String = "all",
        @Query("sort") sort: String = "created",
        @Query("direction") direction: String = "desc"
    ): List<SquareReposNetworkEntity>
}