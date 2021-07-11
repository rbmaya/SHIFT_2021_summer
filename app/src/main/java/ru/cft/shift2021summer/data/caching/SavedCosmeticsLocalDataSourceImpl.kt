package ru.cft.shift2021summer.data.caching

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedCosmeticsLocalDataSourceImpl @Inject constructor(
    private val savedCosmeticsDao: SavedCosmeticsDao
) : SavedCosmeticsDataSource {
    override suspend fun saveCosmetics(cosmetics: List<Cosmetic>) =
        savedCosmeticsDao.saveCosmetics(cosmetics)

    override suspend fun getCosmetics(): List<Cosmetic> = savedCosmeticsDao.getCosmetics()

    override suspend fun getCosmeticByName(name: String): List<Cosmetic> =
        savedCosmeticsDao.getCosmeticByName(name)

    override suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite) =
        savedCosmeticsDao.changeCosmeticIsFavorite(cosmeticIsFavorite)

    override suspend fun getFavoriteCosmetics(): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmetics()

    override suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmeticsByName(name)

}