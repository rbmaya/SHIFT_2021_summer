package ru.cft.shift2021summer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.cft.shift2021summer.data.caching.SavedCosmeticsDao
import ru.cft.shift2021summer.domain.Cosmetic

@Database(entities = [Cosmetic::class], exportSchema = false, version = 6)
abstract class CosmeticDatabase : RoomDatabase() {
    abstract fun getCosmeticDao(): SavedCosmeticsDao
}

