package io.github.occultus73.poqrepositories.framework.datasource.cache.mappers

import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.util.EntityMapper
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<SquareReposCacheEntity, SquareReposItem> {
    override fun mapFromEntity(entity: SquareReposCacheEntity): SquareReposItem {
        return SquareReposItem(
            id = entity.id,
            description = entity.description,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: SquareReposItem): SquareReposCacheEntity {
        return SquareReposCacheEntity(
            id = domainModel.id,
            description = domainModel.description,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<SquareReposCacheEntity>): List<SquareReposItem> {
        return entities.map { mapFromEntity(it) }
    }
}