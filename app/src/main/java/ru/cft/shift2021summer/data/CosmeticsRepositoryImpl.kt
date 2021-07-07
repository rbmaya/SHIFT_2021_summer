package ru.cft.shift2021summer.data

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticsRepository
import javax.inject.Inject

class CosmeticsRepositoryImpl @Inject constructor(
    private val cosmeticsDataSource: CosmeticsDataSource
    ) : CosmeticsRepository {
    override suspend fun getCosmetics(): List<Cosmetic> = cosmeticsDataSource.getCosmetics()
}