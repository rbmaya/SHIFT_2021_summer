package ru.cft.shift2021summer.domain

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class CosmeticIsFavorite(
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean

)
