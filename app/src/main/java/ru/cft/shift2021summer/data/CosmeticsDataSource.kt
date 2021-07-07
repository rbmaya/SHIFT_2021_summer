package ru.cft.shift2021summer.data

import ru.cft.shift2021summer.domain.Cosmetic

interface CosmeticsDataSource {
    suspend fun getCosmetics(): List<Cosmetic>
}