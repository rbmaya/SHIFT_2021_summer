package ru.cft.shift2021summer.data.caching

import ru.cft.shift2021summer.domain.Cosmetic

interface SavedCosmeticsDataSource {
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    suspend fun getCosmetics(): List<Cosmetic>

    suspend fun getCosmeticByName(name: String): List<Cosmetic>
}