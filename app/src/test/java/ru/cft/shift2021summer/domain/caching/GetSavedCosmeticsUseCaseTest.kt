package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS

@RunWith(MockitoJUnitRunner::class)
class GetSavedCosmeticsUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getCosmetics() } returns ALL_COSMETICS
    }

    private val getSavedCosmeticsUseCase = GetSavedCosmeticsUseCase(repository)

    @Test
    fun `get saved cosmetics EXPECT call get saved cosmetics repository`() = runBlocking {
        assertEquals(getSavedCosmeticsUseCase(), ALL_COSMETICS)
    }
}