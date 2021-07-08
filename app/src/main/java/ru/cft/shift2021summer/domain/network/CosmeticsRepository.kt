package ru.cft.shift2021summer.domain.network

import ru.cft.shift2021summer.domain.Cosmetic


interface CosmeticsRepository{
    suspend fun getCosmetics(): List<Cosmetic>
}