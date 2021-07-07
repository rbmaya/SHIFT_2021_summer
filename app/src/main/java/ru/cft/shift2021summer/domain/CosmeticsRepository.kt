package ru.cft.shift2021summer.domain


interface CosmeticsRepository{
    suspend fun getCosmetics(): List<Cosmetic>
}