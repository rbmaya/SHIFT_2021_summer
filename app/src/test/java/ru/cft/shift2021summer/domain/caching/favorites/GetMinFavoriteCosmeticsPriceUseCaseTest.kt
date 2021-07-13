package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetMinFavoriteCosmeticsPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getMinFavoriteCosmeticsPrice() } returns PRICE_MIN_VAL
    }

    private val getMinFavoriteCosmeticsPriceUseCase = GetMinFavoriteCosmeticsPriceUseCase(repository)

    @Test
    fun `get min favorite cosmetics price EXPECT call get min favorite cosmetics price repository`() = runBlocking {
        Assert.assertEquals(getMinFavoriteCosmeticsPriceUseCase(), PRICE_MIN_VAL)
    }
}