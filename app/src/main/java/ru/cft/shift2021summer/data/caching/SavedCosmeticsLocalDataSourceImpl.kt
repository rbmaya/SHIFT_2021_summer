package ru.cft.shift2021summer.data.caching

import ru.cft.shift2021summer.domain.Cosmetic
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
}