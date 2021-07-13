package ru.cft.shift2021summer.domain.network

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS

@RunWith(MockitoJUnitRunner::class)
class GetCosmeticsUseCaseTest {
    private val repository: CosmeticsRepository = mockk {
        coEvery { getCosmetics() } returns ALL_COSMETICS
    }

    private val getCosmeticsUseCase = GetCosmeticsUseCase(repository)

    @Test
    fun `get cosmetics EXPECT call get cosmetics repository`() = runBlocking {
        assertEquals(getCosmeticsUseCase(), ALL_COSMETICS)
    }
}