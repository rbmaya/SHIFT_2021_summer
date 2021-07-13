package ru.cft.shift2021summer.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Cosmetic (
    @PrimaryKey
    val id: Long,
    val name: String,
    val brand: String,
    val price: Float,
    @SerializedName("rating")
    val starRating: String?,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("product_link")
    val productLink: String,
    @SerializedName("product_type")
    @ColumnInfo(name = "product_type")
    val productType: String,
    val description: String,
    var isFavorite: Boolean = false
) : Serializable
