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

    @Update(entity = Cosmetic::class)
    suspend fun changeCosmeticIsFavorite(cosmeticIsFavorite: CosmeticIsFavorite)

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1")
    suspend fun getFavoriteCosmetics(): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE isFavorite LIKE 1 AND name LIKE '%' || (:name) || '%'")
    suspend fun getFavoriteCosmeticsByName(name: String): List<Cosmetic>
}