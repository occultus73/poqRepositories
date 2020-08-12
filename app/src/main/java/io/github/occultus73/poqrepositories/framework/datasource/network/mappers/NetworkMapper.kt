package io.github.occultus73.poqrepositories.framework.datasource.network.mappers

import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.util.EntityMapper
import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<SquareReposNetworkEntity, SquareReposItem> {
    override fun mapFromEntity(entity: SquareReposNetworkEntity): SquareReposItem {
        return SquareReposItem(
            id = entity.id,
            description = entity.description ?: "No Description",
            name = entity.name ?: "No Name"
        )
    }

    override fun mapToEntity(domainModel: SquareReposItem): SquareReposNetworkEntity {
        return SquareReposNetworkEntity(
            id = domainModel.id,
            description = domainModel.description,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<SquareReposNetworkEntity>): List<SquareReposItem> {
        return entities.map { mapFromEntity(it) }
    }
}