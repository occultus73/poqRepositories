package io.github.occultus73.poqrepositories.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SquareReposNetworkEntity(

    @SerializedName("pk")
    @Expose
    var id: Int,

    @SerializedName("category")
    @Expose
    val description: String,

    @SerializedName("category")
    @Expose
    val name: String
)