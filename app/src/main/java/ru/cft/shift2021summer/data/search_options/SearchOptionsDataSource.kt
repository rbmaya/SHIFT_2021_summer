package ru.cft.shift2021summer.data.search_options

import ru.cft.shift2021summer.domain.search_options.SearchGroup

interface SearchOptionsDataSource {
    fun getAllGroups(): List<SearchGroup>
}