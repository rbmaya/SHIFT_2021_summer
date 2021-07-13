package ru.cft.shift2021summer.domain.caching

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS

@RunWith(MockitoJUnitRunner::class)
class SaveCosmeticsUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { saveCosmetics(ALL_COSMETICS) } just runs
    }

    private val saveCosmeticsUseCase = SaveCosmeticsUseCase(repository)

    @Test
    fun `save cosmetics EXPECT call save cosmetics repository`() = runBlocking {
        saveCosmeticsUseCase(ALL_COSMETICS)
        coVerify {
            repository.saveCosmetics(ALL_COSMETICS)
        }
    }
}