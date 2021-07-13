package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteCosmeticsByPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL) } returns FAVORITE_COSMETICS_BY_PRICE
    }

    private val getFavoriteCosmeticsByPriceUseCase = GetFavoriteCosmeticsByPriceUseCase(repository)

    @Test
    fun `get favorite cosmetics by price EXPECT call get favorite cosmetics by price repository`() = runBlocking {
        assertEquals(getFavoriteCosmeticsByPriceUseCase(PRICE_MIN_VAL, PRICE_MAX_VAL), FAVORITE_COSMETICS_BY_PRICE)
    }
}