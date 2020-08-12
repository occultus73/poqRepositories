package io.github.occultus73.poqrepositories.di

import io.github.occultus73.poqrepositories.business.data.network.NetworkDataSource
import io.github.occultus73.poqrepositories.business.data.cache.CacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.occultus73.poqrepositories.business.interactors.GetSquareReposItems
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetSquareReposItems(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetSquareReposItems{
        return GetSquareReposItems(cacheDataSource, networkDataSource)
    }
}














