package io.github.occultus73.poqrepositories.framework.datasource.network

import androidx.lifecycle.LiveData
import com.codingwithmitch.openapi.util.GenericApiResponse
import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposItem
import retrofit2.http.*

interface RepositoriesService {

    //Documentation: https://developer.github.com/v3/repos/#list-organization-repositories

    @GET("orgs/{org}/repos")
    fun downloadOrgRepositories(
        @Path("org") org: String = "square",
        @Query("type") type: String = "all",
        @Query("sort") sort: String = "created",
        @Query("direction") direction: String = "desc"
    ): LiveData<GenericApiResponse<List<SquareReposItem>>>
}