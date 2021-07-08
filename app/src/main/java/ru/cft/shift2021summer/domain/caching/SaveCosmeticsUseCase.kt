package ru.cft.shift2021summer.domain.caching

import ru.cft.shift2021summer.domain.Cosmetic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCosmeticsUseCase @Inject constructor(
    private val savedCosmeticsRepository: SavedCosmeticsRepository
) {
    suspend operator fun invoke(cosmetics: List<Cosmetic>) =
        savedCosmeticsRepository.saveCosmetics(cosmetics)
}