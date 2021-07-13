package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL

@RunWith(MockitoJUnitRunner::class)
class GetMaxCosmeticsPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getMaxCosmeticsPrice() } returns PRICE_MAX_VAL
    }

    private val getMaxCosmeticsPriceUseCase = GetMaxCosmeticsPriceUseCase(repository)

    @Test
    fun `get max cosmetics price EXPECT call get max cosmetics price repository`() = runBlocking {
        assertEquals(getMaxCosmeticsPriceUseCase(), PRICE_MAX_VAL)
    }
}