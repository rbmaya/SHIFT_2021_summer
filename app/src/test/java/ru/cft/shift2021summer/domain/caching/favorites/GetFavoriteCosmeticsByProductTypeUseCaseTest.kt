package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteCosmeticsByProductTypeUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmeticsByProductType(COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
    }

    private val getFavoriteCosmeticsByProductTypeUseCase = GetFavoriteCosmeticsByProductTypeUseCase(repository)

    @Test
    fun `get favorite cosmetics by product type EXPECT call get favorite cosmetics by product type repository`() = runBlocking {
        assertEquals(getFavoriteCosmeticsByProductTypeUseCase(COSMETIC_1.productType), COSMETICS_BY_PRODUCT_TYPE1)
    }
}