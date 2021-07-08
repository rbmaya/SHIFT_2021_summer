package ru.cft.shift2021summer.data.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cft.shift2021summer.domain.Cosmetic

@Database(entities = [Cosmetic::class], version = 1)
abstract class CosmeticDatabase : RoomDatabase() {
    abstract fun getCosmeticDao(): SavedCosmeticsDao
}