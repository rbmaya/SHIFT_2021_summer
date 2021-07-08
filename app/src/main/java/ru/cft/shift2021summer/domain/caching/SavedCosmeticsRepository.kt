package ru.cft.shift2021summer.domain.caching

import ru.cft.shift2021summer.domain.Cosmetic

interface SavedCosmeticsRepository {
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    suspend fun getCosmetics(): List<Cosmetic>
}