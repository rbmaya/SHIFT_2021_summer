package ru.cft.shift2021summer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift2021summer.data.network.CosmeticsApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).readTimeout(15, TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://makeup-api.herokuapp.com/api/v1/")
            .build()

    @Singleton
    @Provides
    fun providesCosmeticsApi(retrofit: Retrofit): CosmeticsApi =
        retrofit.create(CosmeticsApi::class.java)
}