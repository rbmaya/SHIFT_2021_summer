package ru.cft.shift2021summer.domain.caching

import ru.cft.shift2021summer.domain.CosmeticIsFavorite
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChangeCosmeticIsFavoriteUseCase @Inject constructor(
    private val savedCosmeticsRepository: SavedCosmeticsRepository
) {
    suspend operator fun invoke(cosmeticIsFavorite: CosmeticIsFavorite) =
        savedCosmeticsRepository.changeCosmeticIsFavorite(cosmeticIsFavorite)
}