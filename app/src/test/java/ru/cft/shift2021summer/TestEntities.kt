package ru.cft.shift2021summer.data

import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite

object TestEnitities {

    val COSMETIC_1 = Cosmetic(
        id = 1L,
        name = "Name1",
        brand = "brand1",
        price = 5.6f,
        starRating = "5.0",
        imageLink = "http://link",
        productLink = "http://product",
        productType = "eyeliner",
        description = "description",
        isFavorite = true
    )

    val COSMETIC_1_IS_FAVORITE = CosmeticIsFavorite(
        id = COSMETIC_1.id,
        isFavorite = true
    )

    val COSMETIC_2 = Cosmetic(
        id = 2L,
        name = "Name2",
        brand = "brand2",
        price = 4.2f,
        starRating = "4.0",
        imageLink = "http://link2",
        productLink = "http://product2",
        productType = "Blush",
        description = "description2",
        isFavorite = false
    )

    val COSMETIC_3 = Cosmetic(
        id = 3L,
        name = "Name3",
        brand = "brand3",
        price = 10.1f,
        starRating = "4.8",
        imageLink = "http://link3",
        productLink = "http://product3",
        productType = "Bronzer",
        description = "description3",
        isFavorite = true
    )

    val COSMETIC_4 = Cosmetic(
        id = 4L,
        name = "Name4",
        brand = "brand4",
        price = 3.56f,
        starRating = "3.6",
        imageLink = "http://link4",
        productLink = "http://product4",
        productType = "Blush",
        description = "description4",
        isFavorite = true
    )

    val COSMETIC_5 = Cosmetic(
        id = 5L,
        name = "Name5",
        brand = "brand5",
        price = 9.1f,
        starRating = "2.0",
        imageLink = "http://link5",
        productLink = "http://product5",
        productType = "Lip liner",
        description = "description5",
        isFavorite = false
    )

    val ALL_COSMETICS = listOf(COSMETIC_1, COSMETIC_2, COSMETIC_3, COSMETIC_4, COSMETIC_5)
    val COSMETICS_BY_NAME1 = listOf(COSMETIC_1)
    val COSMETICS_BY_BRAND1 = listOf(COSMETIC_1)
    val COSMETICS_BY_PRODUCT_TYPE1 = listOf(COSMETIC_1)
    val PRICE_MIN_VAL = 2f
    val PRICE_MAX_VAL = 9f
    val COSMETICS_BY_PRICE = listOf(COSMETIC_1, COSMETIC_2, COSMETIC_4)
    val FAVORITE_COSMETICS = listOf(COSMETIC_1, COSMETIC_3, COSMETIC_4)
    val FAVORITE_COSMETICS_BY_PRICE = listOf(COSMETIC_1, COSMETIC_4)
}
