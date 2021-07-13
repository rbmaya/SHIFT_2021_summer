package ru.cft.shift2021summer.domain.caching

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite

interface SavedCosmeticsRepository {
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    suspend fun getCosmetics(): List<Cosmetic>

    suspend fun getCosmeticByName(name: String): List<Cosmetic>

    suspend fun getCosmeticByBrand(name: String): List<Cosmetic>

    suspend fun getCosmeticByProductType(name: String): List<Cosmetic>

    suspend fun getCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic>

    suspend fun getMinCosmeticsPrice(): Float

    suspend fun getMaxCosmeticsPrice(): Float

    suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite)

    suspend fun getFavoriteCosmetics(): List<Cosmetic>

    suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic>

    suspend fun getFavoriteCosmeticsByBrand(name: String): List<Cosmetic>

    suspend fun getFavoriteCosmeticsByProductType(name: String): List<Cosmetic>

    suspend fun getMinFavoriteCosmeticsPrice(): Float

    suspend fun getMaxFavoriteCosmeticsPrice(): Float

    suspend fun getFavoriteCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic>
}