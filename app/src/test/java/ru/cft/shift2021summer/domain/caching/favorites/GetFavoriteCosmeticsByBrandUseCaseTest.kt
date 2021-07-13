package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

class GetFavoriteCosmeticsByBrandUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmeticsByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
    }

    private val getFavoriteCosmeticsByBrandUseCase = GetFavoriteCosmeticsByBrandUseCase(repository)

    @Test
    fun `get favorite cosmetics by brand EXPECT call get favorite cosmetics by brand repository`() = runBlocking {
        assertEquals(getFavoriteCosmeticsByBrandUseCase(COSMETIC_1.brand), COSMETICS_BY_BRAND1)
    }
}