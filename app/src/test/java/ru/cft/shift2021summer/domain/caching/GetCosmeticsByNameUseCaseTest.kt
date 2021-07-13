package ru.cft.shift2021summer.domain.caching

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1

@RunWith(MockitoJUnitRunner::class)
class GetCosmeticsByNameUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { getCosmeticByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
    }

    private val getCosmeticByNameUseCase = GetCosmeticByNameUseCase(repository)

    @Test
    fun `get cosmetics by name EXPECT call get cosmetics by name repository`() = runBlocking {
        assertEquals(getCosmeticByNameUseCase(COSMETIC_1.name), COSMETICS_BY_NAME1)
    }
}