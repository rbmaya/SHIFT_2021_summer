package ru.cft.shift2021summer.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.shift2021summer.domain.Cosmetic

interface CosmeticsApi {

    @GET("products.json")
    suspend fun getCosmetics(): List<Cosmetic>

    @GET("products.json")
    suspend fun getCosmeticsByType(
        @Query("product_type") productType: String
    ): List<Cosmetic>

    @GET("products.json")
    suspend fun getCosmeticsByBrand(
        @Query("brand") brand: String
    ): List<Cosmetic>

    @GET("products.json")
    suspend fun getCosmeticsByTags(
        @Query("product_tags") tags: String
    )
}