package ru.cft.shift2021summer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2021summer.data.search_options.SearchOptionsDataSource
import ru.cft.shift2021summer.data.search_options.SearchOptionsLocalDataSourceImpl
import ru.cft.shift2021summer.data.search_options.SearchOptionsRepositoryImpl
import ru.cft.shift2021summer.domain.search_options.SearchOptionsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchOptionsRepositoryBindModule {
    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindSearchOptionsLocalDataSourceImpl_to_SearchOptionsDataSource(
        searchOptionsLocalDataSourceImpl: SearchOptionsLocalDataSourceImpl
    ): SearchOptionsDataSource

    @Binds
    @Singleton
    @Suppress("FunctionName")
    fun bindSearchOptionsRepositoryImpl_to_SearchOptionsRepository(
        searchOptionsRepositoryImpl: SearchOptionsRepositoryImpl
    ): SearchOptionsRepository
}