package ru.cft.shift2021summer

import javax.inject.Inject


class CosmeticsRepository @Inject constructor(){
    private val cosmetics = mutableListOf(
        Cosmetic(
            id = 1,
            name = "Mineral Fusion Blush",
            brand = "Mineral Fusion",
            price = "32.0",
            starRating = "5.0"
        ),
        Cosmetic(
            id = 2,
            name = "L'Oreal Paris Visible Lift Blur Blush",
            brand = "L'oreal",
            price = "15.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 3,
            name = "Wet n Wild ProLine Graphic Marker Eyeliner",
            brand = "Wet N Wild",
            price = "6.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 4,
            name = "Eye Shadow Palette Seduction",
            brand = "Lotus Cosmetics Usa",
            price = "5.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 5,
            name = "Mineral Fusion Blush",
            brand = "Mineral Fusion",
            price = "32.0",
            starRating = "5.0"
        ),
        Cosmetic(
            id = 6,
            name = "L'Oreal Paris Visible Lift Blur Blush",
            brand = "L'oreal",
            price = "15.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 7,
            name = "Wet n Wild ProLine Graphic Marker Eyeliner",
            brand = "Wet N Wild",
            price = "6.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 8,
            name = "Eye Shadow Palette Seduction",
            brand = "Lotus Cosmetics Usa",
            price = "5.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 9,
            name = "Mineral Fusion Blush",
            brand = "Mineral Fusion",
            price = "32.0",
            starRating = "5.0"
        ),
        Cosmetic(
            id = 10,
            name = "L'Oreal Paris Visible Lift Blur Blush",
            brand = "L'oreal",
            price = "15.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 11,
            name = "Wet n Wild ProLine Graphic Marker Eyeliner",
            brand = "Wet N Wild",
            price = "6.99",
            starRating = "unrated"
        ),
        Cosmetic(
            id = 12,
            name = "Eye Shadow Palette Seduction",
            brand = "Lotus Cosmetics Usa",
            price = "5.99",
            starRating = "unrated"
        )
    )

    fun getAll() = cosmetics

    fun getById(id: Long) = cosmetics.firstOrNull { it.id == id }

    fun set(cosmetic: Cosmetic){
        val index = cosmetics.indexOfFirst { it.id == cosmetic.id }
        cosmetics[index] = cosmetic
    }
}