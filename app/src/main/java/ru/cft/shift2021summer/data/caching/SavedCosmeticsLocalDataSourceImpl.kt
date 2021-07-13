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

    override suspend fun getCosmeticByBrand(name: String): List<Cosmetic> =
        savedCosmeticsDao.getCosmeticByBrand(name)

    override suspend fun getCosmeticByProductType(name: String): List<Cosmetic> =
        savedCosmeticsDao.getCosmeticByProductType(name)

    override suspend fun getCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic> =
        savedCosmeticsDao.getCosmeticsByPrice(valueFrom = valueFrom, valueTo = valueTo)

    override suspend fun getMinCosmeticsPrice(): Float = savedCosmeticsDao.getMinCosmeticsPrice()

    override suspend fun getMaxCosmeticsPrice(): Float = savedCosmeticsDao.getMaxCosmeticsPrice()

    override suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite) =
        savedCosmeticsDao.changeCosmeticIsFavorite(cosmeticIsFavorite)

    override suspend fun getFavoriteCosmetics(): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmetics()

    override suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmeticsByName(name)

    override suspend fun getFavoriteCosmeticsByBrand(name: String): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmeticsByBrand(name)

    override suspend fun getFavoriteCosmeticsByProductType(name: String): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmeticsByProductType(name)

    override suspend fun getMinFavoriteCosmeticsPrice(): Float =
        savedCosmeticsDao.getMinFavoriteCosmeticsPrice()

    override suspend fun getMaxFavoriteCosmeticsPrice(): Float =
        savedCosmeticsDao.getMaxFavoriteCosmeticsPrice()

    override suspend fun getFavoriteCosmeticsByPrice(
        valueFrom: Float,
        valueTo: Float
    ): List<Cosmetic> =
        savedCosmeticsDao.getFavoriteCosmeticsByPrice(
            valueFrom = valueFrom,
            valueTo = valueTo
        )
}