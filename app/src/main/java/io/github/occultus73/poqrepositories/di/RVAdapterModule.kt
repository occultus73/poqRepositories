package io.github.occultus73.poqrepositories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.occultus73.poqrepositories.framework.presentation.ui.RVAdapter
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RVAdapterModule {

    @Singleton
    @Provides
    fun providesRVAdapter(): RVAdapter{
        return RVAdapter()
    }
}