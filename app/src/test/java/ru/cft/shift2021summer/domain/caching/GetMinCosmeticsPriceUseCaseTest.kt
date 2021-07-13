package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL

@RunWith(MockitoJUnitRunner::class)
class GetMinCosmeticsPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getMinCosmeticsPrice() } returns PRICE_MIN_VAL
    }

    private val getMinCosmeticsUseCase = GetMinCosmeticsPriceUseCase(repository)

    @Test
    fun `get min cosmetics price EXPECT call get min cosmetics price repository`() = runBlocking {
        Assert.assertEquals(getMinCosmeticsUseCase(), PRICE_MIN_VAL)
    }
}