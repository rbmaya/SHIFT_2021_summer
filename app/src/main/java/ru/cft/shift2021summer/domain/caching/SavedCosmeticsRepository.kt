package ru.cft.shift2021summer.domain.caching

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite

interface SavedCosmeticsRepository {
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    suspend fun getCosmetics(): List<Cosmetic>

    suspend fun getCosmeticByName(name: String): List<Cosmetic>

    suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite)

    suspend fun getFavoriteCosmetics(): List<Cosmetic>

    suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic>
}