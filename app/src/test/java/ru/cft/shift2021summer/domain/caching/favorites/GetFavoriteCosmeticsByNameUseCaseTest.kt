package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteCosmeticsByNameUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmeticsByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
    }

    private val getFavoriteCosmeticsByNameUseCase = GetFavoriteCosmeticsByNameUseCase(repository)

    @Test
    fun `get favorite cosmetics by name EXPECT call get favorite cosmetics by name repository`() = runBlocking {
        Assert.assertEquals(getFavoriteCosmeticsByNameUseCase(COSMETIC_1.name), COSMETICS_BY_NAME1)
    }
}