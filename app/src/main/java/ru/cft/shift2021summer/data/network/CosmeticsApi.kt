package ru.cft.shift2021summer.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.shift2021summer.domain.Cosmetic

interface CosmeticsApi {

    @GET("products.json")
    suspend fun getCosmetics(): List<Cosmetic>
}