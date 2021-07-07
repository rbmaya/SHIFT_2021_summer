package ru.cft.shift2021summer.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cosmetic (
    val id: Long,
    val name: String,
    val brand: String,
    val price: String,
    @SerializedName("rating")
    val starRating: String?,
    @SerializedName("image_link")
    val imageLink: String,
    @SerializedName("product_link")
    val productLink: String,
    val description: String
) : Serializable
