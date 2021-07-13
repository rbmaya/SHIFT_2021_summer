package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1

@RunWith(MockitoJUnitRunner::class)
class GetCosmeticsByProductTypeUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getCosmeticByProductType(TestEnitities.COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
    }

    private val getCosmeticsByProductTypeUseCase = GetCosmeticByProductTypeUseCase(repository)

    @Test
    fun `get cosmetics by product type EXPECT call get cosmetics by product type repository`() = runBlocking {
        Assert.assertEquals(getCosmeticsByProductTypeUseCase(TestEnitities.COSMETIC_1.productType), COSMETICS_BY_PRODUCT_TYPE1)
    }
}