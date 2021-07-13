package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetMaxFavoriteCosmeticsPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getMaxFavoriteCosmeticsPrice() } returns PRICE_MAX_VAL
    }

    private val getMaxFavoriteCosmeticsPriceUseCase = GetMaxFavoriteCosmeticsPriceUseCase(repository)

    @Test
    fun `get max favorite cosmetics price EXPECT call get max favorite cosmetics price repository`() = runBlocking {
        assertEquals(getMaxFavoriteCosmeticsPriceUseCase(), PRICE_MAX_VAL)
    }
}