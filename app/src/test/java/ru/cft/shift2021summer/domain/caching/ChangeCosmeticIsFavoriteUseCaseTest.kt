package ru.cft.shift2021summer.domain.caching

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1_IS_FAVORITE

@RunWith(MockitoJUnitRunner::class)
class ChangeCosmeticIsFavoriteUseCaseTest {
    private val repository: SavedCosmeticsRepository = mockk {
        coEvery { changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE) } just runs
    }

    private val changeCosmeticIsFavoriteUseCase = ChangeCosmeticIsFavoriteUseCase(repository)

    @Test
    fun `change cosmetic is favorite EXPECT call change cosmetic is favorite repository`() = runBlocking {
        changeCosmeticIsFavoriteUseCase(COSMETIC_1_IS_FAVORITE)
        coVerify {
            repository.changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE)
        }
    }
}