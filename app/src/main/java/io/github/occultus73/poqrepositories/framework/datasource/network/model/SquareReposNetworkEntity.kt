package io.github.occultus73.poqrepositories.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SquareReposNetworkEntity(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("description")
    @Expose
    var description: String?,

    @SerializedName("name")
    @Expose
    var name: String?
){
    init {
        if (description == null){
            description = "No Description Given"
        }
        if (name == null){
            name = "No Name?!"
        }
    }

}