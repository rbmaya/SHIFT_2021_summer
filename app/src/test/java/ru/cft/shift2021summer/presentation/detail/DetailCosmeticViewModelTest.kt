package ru.cft.shift2021summer.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.TestCoroutineRule
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1_IS_FAVORITE
import ru.cft.shift2021summer.domain.caching.ChangeCosmeticIsFavoriteUseCase
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailCosmeticViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: DetailCosmeticViewModel

    private val savedCosmeticsRepository: SavedCosmeticsRepository = mockk {
        coEvery { changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE) } just runs
    }

    @Before
    fun setUp() {
        viewModel = DetailCosmeticViewModel(
            changeCosmeticIsFavoriteUseCase = ChangeCosmeticIsFavoriteUseCase(
                savedCosmeticsRepository
            ),
            COSMETIC_1
        )
    }

    @Test
    fun `change cosmetic is favorite EXPECT call change cosmetic is favorite from repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.changeIsFavoriteCosmetic(COSMETIC_1.id, true)
            coVerify {
                savedCosmeticsRepository.changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE)
            }
        }

}