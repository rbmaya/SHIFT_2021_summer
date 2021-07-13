package ru.cft.shift2021summer.data.caching

import androidx.room.*
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite

@Dao
interface SavedCosmeticsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    @Query("SELECT * FROM cosmetic")
    suspend fun getCosmetics(): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE name LIKE '%' || (:name) || '%'")
    suspend fun getCosmeticByName(name: String): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE brand LIKE (:name)")
    suspend fun getCosmeticByBrand(name: String): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE product_type LIKE (:name)")
    suspend fun getCosmeticByProductType(name: String): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE price >= :valueFrom AND price <= :valueTo")
    suspend fun getCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic>

    @Query("SELECT MIN(price) FROM cosmetic")
    suspend fun getMinCosmeticsPrice(): Float

    @Query("SELECT MAX(price) FROM cosmetic")
    suspend fun getMaxCosmeticsPrice(): Float

    @Update(entity = Cosmetic::class)
    suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite)

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1")
    suspend fun getFavoriteCosmetics(): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1 AND name LIKE '%' || (:name) || '%'")
    suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1 AND brand LIKE (:name)")
    suspend fun getFavoriteCosmeticsByBrand(name: String): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1 AND product_type LIKE (:name)")
    suspend fun getFavoriteCosmeticsByProductType(name: String): List<Cosmetic>

    @Query("SELECT MIN(price) FROM cosmetic WHERE isFavorite LIKE 1")
    suspend fun getMinFavoriteCosmeticsPrice(): Float

    @Query("SELECT MAX(price) FROM cosmetic WHERE isFavorite LIKE 1")
    suspend fun getMaxFavoriteCosmeticsPrice(): Float

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1 AND price >= :valueFrom AND price <= :valueTo")
    suspend fun getFavoriteCosmeticsByPrice(valueFrom: Float, valueTo: Float): List<Cosmetic>

}