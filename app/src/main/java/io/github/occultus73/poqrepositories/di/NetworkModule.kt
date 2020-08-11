package io.github.occultus73.poqrepositories.di

import android.content.Context
import com.codingwithmitch.daggerhiltplayground.business.data.network.NetworkDataSource
import com.codingwithmitch.daggerhiltplayground.business.data.network.NetworkDataSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.util.EntityMapper
import io.github.occultus73.poqrepositories.framework.datasource.network.SquareReposRetrofitService
import io.github.occultus73.poqrepositories.framework.datasource.network.SquareReposRetrofitServiceImpl
import io.github.occultus73.poqrepositories.framework.datasource.network.mappers.NetworkMapper
import io.github.occultus73.poqrepositories.framework.datasource.network.model.SquareReposNetworkEntity
import io.github.occultus73.poqrepositories.framework.datasource.network.retrofit.SquareReposRetrofit
import io.github.occultus73.weatherforecast.model.network.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<SquareReposNetworkEntity, SquareReposItem> {
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {
        val connectivityInterceptor = ConnectivityInterceptor(context)
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideSquareReposService(retrofit: Retrofit.Builder): SquareReposRetrofit {
        return retrofit
            .build()
            .create(SquareReposRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideSquareReposRetrofitService(squareReposRetrofit: SquareReposRetrofit): SquareReposRetrofitService {
        return SquareReposRetrofitServiceImpl(squareReposRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        squareReposRetrofitService: SquareReposRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(squareReposRetrofitService, networkMapper)
    }


}