package ru.cft.shift2021summer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2021summer.data.CosmeticsDataSource
import ru.cft.shift2021summer.data.CosmeticsRemoteDataSourceImpl
import ru.cft.shift2021summer.data.CosmeticsRepositoryImpl
import ru.cft.shift2021summer.domain.CosmeticsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindModule {

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