package ru.cft.shift2021summer.domain.search_options

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllGroupsUseCase @Inject constructor(private val searchOptionsRepository: SearchOptionsRepository) {
    operator fun invoke() = searchOptionsRepository.getAllGroups()
}