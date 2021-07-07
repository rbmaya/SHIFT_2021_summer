package ru.cft.shift2021summer.data

import ru.cft.shift2021summer.domain.Cosmetic
import javax.inject.Inject

class CosmeticsRemoteDataSourceImpl @Inject constructor(
    private val cosmeticsApi: CosmeticsApi
    ) : CosmeticsDataSource {
    override suspend fun getCosmetics(): List<Cosmetic> = cosmeticsApi.getCosmetics()
}