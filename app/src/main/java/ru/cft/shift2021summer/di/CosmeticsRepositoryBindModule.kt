package ru.cft.shift2021summer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2021summer.data.network.CosmeticsDataSource
import ru.cft.shift2021summer.data.network.CosmeticsRemoteDataSourceImpl
import ru.cft.shift2021summer.data.network.CosmeticsRepositoryImpl
import ru.cft.shift2021summer.domain.network.CosmeticsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CosmeticsRepositoryBindModule {

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindCosmeticsRemoteDataSourceImpl_to_CosmeticsDataSource(
        cosmeticsRemoteDataSourceImpl: CosmeticsRemoteDataSourceImpl
    ): CosmeticsDataSource


    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindCosmeticsRepositoryImpl_to_CosmeticsRepository(
        cosmeticsRepositoryImpl: CosmeticsRepositoryImpl
    ): CosmeticsRepository
}