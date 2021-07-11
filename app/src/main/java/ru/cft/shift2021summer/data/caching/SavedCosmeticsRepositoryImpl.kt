package ru.cft.shift2021summer.data.caching

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedCosmeticsRepositoryImpl @Inject constructor(
    private val savedCosmeticsDataSource: SavedCosmeticsDataSource
) :
    SavedCosmeticsRepository {
    override suspend fun saveCosmetics(cosmetics: List<Cosmetic>) =
        savedCosmeticsDataSource.saveCosmetics(cosmetics)

    override suspend fun getCosmetics(): List<Cosmetic> =
        savedCosmeticsDataSource.getCosmetics()

    override suspend fun getCosmeticByName(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getCosmeticByName(name)

    override suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite) =
        savedCosmeticsDataSource.changeCosmeticIsFavorite(cosmeticIsFavorite)

    override suspend fun getFavoriteCosmetics(): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmetics()

    override suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmeticsByName(name)
}