package io.github.occultus73.poqrepositories.framework.datasource.network.model


data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)