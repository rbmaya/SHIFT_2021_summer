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

    override suspend fun getCosmeticByBrand(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getCosmeticByBrand(name)

    override suspend fun getCosmeticByProductType(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getCosmeticByProductType(name)

    override suspend fun getCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic> =
        savedCosmeticsDataSource.getCosmeticsByPrice(valueFrom = valueFrom, valueTo = valueTo)

    override suspend fun getMinCosmeticsPrice(): Float = savedCosmeticsDataSource.getMinCosmeticsPrice()

    override suspend fun getMaxCosmeticsPrice(): Float = savedCosmeticsDataSource.getMaxCosmeticsPrice()

    override suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite) =
        savedCosmeticsDataSource.changeCosmeticIsFavorite(cosmeticIsFavorite)

    override suspend fun getFavoriteCosmetics(): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmetics()

    override suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmeticsByName(name)

    override suspend fun getFavoriteCosmeticsByBrand(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmeticsByBrand(name)

    override suspend fun getFavoriteCosmeticsByProductType(name: String): List<Cosmetic> =
        savedCosmeticsDataSource.getFavoriteCosmeticsByProductType(name)

    override suspend fun getMinFavoriteCosmeticsPrice(): Float =
        savedCosmeticsDataSource.getMinFavoriteCosmeticsPrice()

    override suspend fun getMaxFavoriteCosmeticsPrice(): Float =
        savedCosmeticsDataSource.getMaxFavoriteCosmeticsPrice()

    override suspend fun getFavoriteCosmeticsByPrice(
        valueFrom: Float,
        valueTo: Float
    ): List<Cosmetic> = savedCosmeticsDataSource.getFavoriteCosmeticsByPrice(
        valueFrom = valueFrom,
        valueTo = valueTo
    )
}