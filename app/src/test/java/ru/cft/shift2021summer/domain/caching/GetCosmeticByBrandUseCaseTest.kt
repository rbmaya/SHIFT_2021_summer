package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1

@RunWith(MockitoJUnitRunner::class)
class GetCosmeticByBrandUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getCosmeticByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
    }

    private val getCosmeticByBrandUseCase = GetCosmeticByBrandUseCase(repository)

    @Test
    fun `get cosmetics by brand EXPECT call get cosmetics by brand repository`() = runBlocking {
        assertEquals(getCosmeticByBrandUseCase(COSMETIC_1.brand), COSMETICS_BY_BRAND1)
    }
}