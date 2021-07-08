package ru.cft.shift2021summer.data.network

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.network.CosmeticsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CosmeticsRepositoryImpl @Inject constructor(
    private val cosmeticsDataSource: CosmeticsDataSource
    ) : CosmeticsRepository {
    override suspend fun getCosmetics(): List<Cosmetic> = cosmeticsDataSource.getCosmetics()
}