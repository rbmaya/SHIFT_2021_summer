package ru.cft.shift2021summer.domain.caching

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCosmeticByProductTypeUseCase @Inject constructor(
    private val savedCosmeticsRepository: SavedCosmeticsRepository
) {
    suspend operator fun invoke(name: String) =
        savedCosmeticsRepository.getCosmeticByProductType(name)
}