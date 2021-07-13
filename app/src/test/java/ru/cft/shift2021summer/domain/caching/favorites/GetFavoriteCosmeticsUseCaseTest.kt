package ru.cft.shift2021summer.domain.caching.favorites

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteCosmeticsUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmetics() } returns FAVORITE_COSMETICS
    }

    private val getFavoriteCosmeticsUseCase = GetFavoriteCosmeticsUseCase(repository)

    @Test
    fun `get favorite cosmetics by product type EXPECT call get favorite cosmetics by product type repository`() = runBlocking {
        assertEquals(getFavoriteCosmeticsUseCase(), FAVORITE_COSMETICS)
    }
}