package io.github.occultus73.poqrepositories.di

import android.content.Context
import androidx.room.Room
import io.github.occultus73.poqrepositories.business.data.cache.CacheDataSource
import io.github.occultus73.poqrepositories.business.data.cache.CacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.util.EntityMapper
import io.github.occultus73.poqrepositories.framework.datasource.cache.SquareReposDaoService
import io.github.occultus73.poqrepositories.framework.datasource.cache.SquareReposDaoServiceImpl
import io.github.occultus73.poqrepositories.framework.datasource.cache.database.SquareReposDao
import io.github.occultus73.poqrepositories.framework.datasource.cache.database.SquareReposDatabase
import io.github.occultus73.poqrepositories.framework.datasource.cache.mappers.CacheMapper
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<SquareReposCacheEntity, SquareReposItem> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideSquareReposDb(@ApplicationContext context: Context): SquareReposDatabase {
        return Room
            .databaseBuilder(
                context,
                SquareReposDatabase::class.java,
                SquareReposDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSquareReposDao(squareReposDatabase: SquareReposDatabase): SquareReposDao {
        return squareReposDatabase.squareReposDao()
    }

    @Singleton
    @Provides
    fun provideSquareReposDaoService(
        squareReposDao: SquareReposDao
    ): SquareReposDaoService {
        return SquareReposDaoServiceImpl(squareReposDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        squareReposDaoService: SquareReposDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(squareReposDaoService, cacheMapper)
    }
}