package ru.cft.shift2021summer.data.search_options

import ru.cft.shift2021summer.domain.search_options.SearchGroup
import ru.cft.shift2021summer.domain.search_options.SearchOptionsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchOptionsRepositoryImpl @Inject constructor(
    private val searchOptionsDataSource: SearchOptionsDataSource
) : SearchOptionsRepository {
    override fun getAllGroups(): List<SearchGroup> = searchOptionsDataSource.getAllGroups()
}