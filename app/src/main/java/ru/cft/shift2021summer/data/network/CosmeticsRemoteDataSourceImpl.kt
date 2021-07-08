package ru.cft.shift2021summer.data.network

import ru.cft.shift2021summer.domain.Cosmetic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CosmeticsRemoteDataSourceImpl @Inject constructor(
    private val cosmeticsApi: CosmeticsApi
    ) : CosmeticsDataSource {
    override suspend fun getCosmetics(): List<Cosmetic> = cosmeticsApi.getCosmetics()
}