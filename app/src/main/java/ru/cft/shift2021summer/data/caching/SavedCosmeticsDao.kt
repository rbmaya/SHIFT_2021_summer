package ru.cft.shift2021summer.data.caching

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.cft.shift2021summer.domain.Cosmetic

@Dao
interface SavedCosmeticsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveCosmetics(cosmetics: List<Cosmetic>)

    @Query("SELECT * FROM cosmetic")
    suspend fun getCosmetics(): List<Cosmetic>

    @Query("SELECT * FROM cosmetic WHERE name=(:name)")
    suspend fun getCosmeticByName(name: String): List<Cosmetic>
}