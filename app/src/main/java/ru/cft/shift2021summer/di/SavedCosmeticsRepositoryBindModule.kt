package ru.cft.shift2021summer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2021summer.data.caching.SavedCosmeticsDataSource
import ru.cft.shift2021summer.data.caching.SavedCosmeticsLocalDataSourceImpl
import ru.cft.shift2021summer.data.caching.SavedCosmeticsRepositoryImpl
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SavedCosmeticsRepositoryBindModule {

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindSavedCosmeticsLocalDataSourceImpl_to_SavedCosmeticsDataSource(
        savedCosmeticsLocalDataSourceImpl: SavedCosmeticsLocalDataSourceImpl
    ): SavedCosmeticsDataSource

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindSavedCosmeticsRepositoryImpl_to_SavedCosmeticsRepository(
        savedCosmeticsRepositoryImpl: SavedCosmeticsRepositoryImpl
    ): SavedCosmeticsRepository
}