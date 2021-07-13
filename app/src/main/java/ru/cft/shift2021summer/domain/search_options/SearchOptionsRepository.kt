package ru.cft.shift2021summer.domain.search_options

interface SearchOptionsRepository {
    fun getAllGroups(): List<SearchGroup>
}