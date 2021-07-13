package ru.cft.shift2021summer.domain.caching

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCosmeticsByPriceUseCase @Inject constructor(
    private val savedCosmeticsRepository: SavedCosmeticsRepository
) {
    suspend operator fun invoke(valueFrom: Float, valueTo: Float) =
        savedCosmeticsRepository.getCosmeticsByPrice(
            valueFrom = valueFrom,
            valueTo = valueTo
        )
}