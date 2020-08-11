package io.github.occultus73.poqrepositories.model


import com.google.gson.annotations.SerializedName

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)