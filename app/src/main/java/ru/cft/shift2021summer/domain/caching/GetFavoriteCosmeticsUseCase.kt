package ru.cft.shift2021summer.domain.caching

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavoriteCosmeticsUseCase @Inject constructor(
    private val savedCosmeticsRepository: SavedCosmeticsRepository
){
    suspend operator fun invoke() = savedCosmeticsRepository.getFavoriteCosmetics()
}