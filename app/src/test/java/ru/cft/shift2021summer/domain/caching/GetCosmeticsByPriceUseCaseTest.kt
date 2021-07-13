package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL

@RunWith(MockitoJUnitRunner::class)
class GetCosmeticsByPriceUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL) } returns COSMETICS_BY_PRICE
    }

    private val getCosmeticsByPriceUseCase = GetCosmeticsByPriceUseCase(repository)

    @Test
    fun `get cosmetics by price EXPECT call get cosmetics by price repository`() = runBlocking {
        assertEquals(getCosmeticsByPriceUseCase(PRICE_MIN_VAL, PRICE_MAX_VAL), COSMETICS_BY_PRICE)
    }
}