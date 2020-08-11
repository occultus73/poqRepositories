package io.github.occultus73.poqrepositories.di

import android.content.Context
import com.codingwithmitch.openapi.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRepositoriesService(okHttpClient: OkHttpClient): SquareReposRetrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(SquareReposRetrofit::class.java)
    }


}