package ru.cft.shift2021summer.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.TestCoroutineRule
import ru.cft.shift2021summer.data.TestEnitities
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL
import ru.cft.shift2021summer.domain.caching.*
import ru.cft.shift2021summer.domain.network.CosmeticsRepository
import ru.cft.shift2021summer.domain.network.GetCosmeticsUseCase

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CosmeticsListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CosmeticsListViewModel

    private val cosmeticsRepository: CosmeticsRepository = mockk {
        coEvery { getCosmetics() } returns ALL_COSMETICS
    }

    private val savedCosmeticsRepository: SavedCosmeticsRepository = mockk {
        coEvery { saveCosmetics(ALL_COSMETICS) } just runs
        coEvery { getCosmetics() } returns ALL_COSMETICS
        coEvery { getCosmeticByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
        coEvery { getCosmeticByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
        coEvery { getCosmeticByProductType(COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
        coEvery { getCosmeticsByPrice(
            PRICE_MIN_VAL,
            PRICE_MAX_VAL
        ) } returns COSMETICS_BY_PRICE
        coEvery { getMinCosmeticsPrice() } returns PRICE_MIN_VAL
        coEvery { getMaxCosmeticsPrice() } returns PRICE_MAX_VAL
        coEvery { changeCosmeticIsFavorite(TestEnitities.COSMETIC_1_IS_FAVORITE) } just runs
    }

    @Before
    fun setUp() {
        viewModel = CosmeticsListViewModel(
            getCosmeticByNameUseCase = GetCosmeticByNameUseCase(savedCosmeticsRepository),
            getCosmeticsUseCase = GetCosmeticsUseCase(cosmeticsRepository),
            getSavedCosmeticsUseCase = GetSavedCosmeticsUseCase(savedCosmeticsRepository),
            saveCosmeticsUseCase = SaveCosmeticsUseCase(savedCosmeticsRepository),
            getCosmeticsByBrandUseCase = GetCosmeticByBrandUseCase(savedCosmeticsRepository),
            getCosmeticsByProductTypeUseCase = GetCosmeticByProductTypeUseCase(
                savedCosmeticsRepository
            ),
            getCosmeticsByPriceUseCase = GetCosmeticsByPriceUseCase(savedCosmeticsRepository),
            getMaxCosmeticsPriceUseCase = GetMaxCosmeticsPriceUseCase(savedCosmeticsRepository),
            getMinCosmeticsPriceUseCase = GetMinCosmeticsPriceUseCase(savedCosmeticsRepository)
        )
    }

    @Test
    fun `load cosmetics from network EXPECT load cosmetics from cosmetics repository and caching into database`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmetics(true)
            coVerifySequence {
                cosmeticsRepository.getCosmetics()
                savedCosmeticsRepository.saveCosmetics(ALL_COSMETICS)
            }
        }

    @Test
    fun `load cosmetics from cache EXPECT load cosmetics from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmetics(false)
            coVerify { savedCosmeticsRepository.getCosmetics() }
        }

    @Test
    fun `query text changed EXPECT load cosmetics by name from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.queryTextChanged(COSMETIC_1.name)
            coVerify { savedCosmeticsRepository.getCosmeticByName(COSMETIC_1.name) }
        }

    @Test
    fun `load cosmetics by brand EXPECT load cosmetics by brand from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmeticsByBrand(COSMETIC_1.brand)
            coVerify { savedCosmeticsRepository.getCosmeticByBrand(COSMETIC_1.brand) }
        }

    @Test
    fun `load cosmetics by product type EXPECT load cosmetics by product type from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmeticsByProductType(COSMETIC_1.productType)
            coVerify { savedCosmeticsRepository.getCosmeticByProductType(COSMETIC_1.productType) }
        }

    @Test
    fun `load cosmetics by price EXPECT load cosmetics by price from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL)
            coVerify { savedCosmeticsRepository.getCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL) }
        }

    @Test
    fun `load price limits EXPECT load price limits from saved cosmetics repository`() =
        testCoroutineRule.runBlockingTest {
            viewModel.loadPriceLimits()
            coVerifyAll {
                savedCosmeticsRepository.getMaxCosmeticsPrice()
                savedCosmeticsRepository.getMinCosmeticsPrice()
            }
        }
}