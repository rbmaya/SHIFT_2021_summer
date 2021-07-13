package ru.cft.shift2021summer.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.TestCoroutineRule
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL
import ru.cft.shift2021summer.domain.caching.SavedCosmeticsRepository
import ru.cft.shift2021summer.domain.caching.favorites.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesCosmeticViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: FavoritesCosmeticViewModel

    private val savedCosmeticsRepository: SavedCosmeticsRepository = mockk {
        coEvery { getFavoriteCosmetics() } returns FAVORITE_COSMETICS
        coEvery { getFavoriteCosmeticsByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
        coEvery { getFavoriteCosmeticsByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
        coEvery { getFavoriteCosmeticsByProductType(COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
        coEvery { getMinFavoriteCosmeticsPrice() } returns PRICE_MIN_VAL
        coEvery { getMaxFavoriteCosmeticsPrice() } returns PRICE_MAX_VAL
        coEvery {
            getFavoriteCosmeticsByPrice(
                PRICE_MIN_VAL,
                PRICE_MAX_VAL
            )
        } returns FAVORITE_COSMETICS_BY_PRICE
    }

    @Before
    fun setUp() {
        viewModel = FavoritesCosmeticViewModel(
            getFavoriteCosmeticsUseCase = GetFavoriteCosmeticsUseCase(savedCosmeticsRepository),
            getFavoriteCosmeticsByBrandUseCase = GetFavoriteCosmeticsByBrandUseCase(savedCosmeticsRepository),
            getFavoriteCosmeticsByNameUseCase = GetFavoriteCosmeticsByNameUseCase(savedCosmeticsRepository),
            getFavoriteCosmeticsByPriceUseCase = GetFavoriteCosmeticsByPriceUseCase(savedCosmeticsRepository),
            getFavoriteCosmeticsByProductTypeUseCase = GetFavoriteCosmeticsByProductTypeUseCase(savedCosmeticsRepository),
            getMaxFavoriteCosmeticsPriceUseCase = GetMaxFavoriteCosmeticsPriceUseCase(savedCosmeticsRepository),
            getMinFavoriteCosmeticsPriceUseCase = GetMinFavoriteCosmeticsPriceUseCase(savedCosmeticsRepository)
        )
    }

    @Test
    fun `load favorite cosmetics from cache EXPECT load favorite cosmetics from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmetics()
            coVerify {
                savedCosmeticsRepository.getFavoriteCosmetics()
            }
        }

    @Test
    fun `query text changed to name EXPECT load favorite cosmetics by name from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.queryTextChanged(COSMETIC_1.name)
            coVerify {
                savedCosmeticsRepository.getFavoriteCosmeticsByName(COSMETIC_1.name)
            }
        }

    @Test
    fun `load favorite cosmetics by brand EXPECT load favorite cosmetics by brand from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmeticsByBrand(COSMETIC_1.brand)
            coVerify {
                savedCosmeticsRepository.getFavoriteCosmeticsByBrand(COSMETIC_1.brand)
            }
        }

    @Test
    fun `load favorite cosmetics by price EXPECT load favorite cosmetics by price from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL)
            coVerify { savedCosmeticsRepository.getFavoriteCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL) }
        }

    @Test
    fun `load price limits EXPECT load price limits from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadPriceLimits()
            coVerifyAll {
                savedCosmeticsRepository.getMaxFavoriteCosmeticsPrice()
                savedCosmeticsRepository.getMinFavoriteCosmeticsPrice()
            }
        }
}