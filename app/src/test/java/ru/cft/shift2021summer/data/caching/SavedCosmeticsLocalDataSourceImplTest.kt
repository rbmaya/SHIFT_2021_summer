package ru.cft.shift2021summer.data.caching

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1_IS_FAVORITE
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL

@RunWith(MockitoJUnitRunner::class)
class SavedCosmeticsLocalDataSourceImplTest {
    private val dao: SavedCosmeticsDao = mockk {
        coEvery { saveCosmetics(ALL_COSMETICS) } just runs
        coEvery { getCosmetics() } returns ALL_COSMETICS
        coEvery { getCosmeticByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
        coEvery { getCosmeticByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
        coEvery { getCosmeticByProductType(COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
        coEvery { getCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL) } returns COSMETICS_BY_PRICE
        coEvery { getMinCosmeticsPrice() } returns PRICE_MIN_VAL
        coEvery { getMaxCosmeticsPrice() } returns PRICE_MAX_VAL
        coEvery { changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE) } just runs
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

    private val dataSource = SavedCosmeticsLocalDataSourceImpl(dao)

    @Test
    fun `save cosmetics EXPECT cosmetics will be saved`() = runBlocking {
        dataSource.saveCosmetics(ALL_COSMETICS)
        coVerify {
            dao.saveCosmetics(ALL_COSMETICS)
        }
    }

    @Test
    fun `get cosmetics EXPECT all cosmetics from database`() = runBlocking {
        assertEquals(dataSource.getCosmetics(), ALL_COSMETICS)
    }

    @Test
    fun `get cosmetics by name EXPECT cosmetics by name from database`() = runBlocking {
        assertEquals(dataSource.getCosmeticByName(COSMETIC_1.name), COSMETICS_BY_NAME1)
    }

    @Test
    fun `get cosmetics by brand EXPECT cosmetics by brand from database`() = runBlocking {
        assertEquals(dataSource.getCosmeticByBrand(COSMETIC_1.brand), COSMETICS_BY_BRAND1)
    }

    @Test
    fun `get cosmetics by product type EXPECT cosmetics by product type from database`() =
        runBlocking {
            assertEquals(
                dataSource.getCosmeticByProductType(COSMETIC_1.productType),
                COSMETICS_BY_PRODUCT_TYPE1
            )
        }

    @Test
    fun `get cosmetics by price EXPECT cosmetics by price from database`() = runBlocking {
        assertEquals(
            dataSource.getCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL),
            COSMETICS_BY_PRICE
        )
    }

    @Test
    fun `get min cosmetics price EXPECT min cosmetics price from database`() = runBlocking {
        assertEquals(dataSource.getMinCosmeticsPrice(), PRICE_MIN_VAL)
    }

    @Test
    fun `get max cosmetics price EXPECT max cosmetics price from database`() = runBlocking {
        assertEquals(dataSource.getMaxCosmeticsPrice(), PRICE_MAX_VAL)
    }

    @Test
    fun `change cosmetic is favorite EXPECT cosmetic with id will be favorite`() = runBlocking {
        dataSource.changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE)
        coVerify {
            dao.changeCosmeticIsFavorite(COSMETIC_1_IS_FAVORITE)
        }
    }

    @Test
    fun `get favorite cosmetics EXPECT all favorite cosmetics from database`() = runBlocking {
        assertEquals(dataSource.getFavoriteCosmetics(), FAVORITE_COSMETICS)
    }

    @Test
    fun `get favorite cosmetics by name EXPECT favorite cosmetics by name from database`() =
        runBlocking {
            assertEquals(dataSource.getFavoriteCosmeticsByName(COSMETIC_1.name), COSMETICS_BY_NAME1)
        }

    @Test
    fun `get favorite cosmetics by brand EXPECT favorite cosmetics by brand from database`() =
        runBlocking {
            assertEquals(
                dataSource.getFavoriteCosmeticsByBrand(COSMETIC_1.brand),
                COSMETICS_BY_BRAND1
            )
        }

    @Test
    fun `get favorite cosmetics by product type EXPECT favorite cosmetics by product type from database`() =
        runBlocking {
            assertEquals(
                dataSource.getFavoriteCosmeticsByProductType(COSMETIC_1.productType),
                COSMETICS_BY_PRODUCT_TYPE1
            )
        }

    @Test
    fun `get favorite cosmetics by price EXPECT favorite cosmetics by price from database`() =
        runBlocking {
            assertEquals(
                dataSource.getFavoriteCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL),
                FAVORITE_COSMETICS_BY_PRICE
            )
        }

    @Test
    fun `get min favorite cosmetics price EXPECT min favorite cosmetics price from database`() =
        runBlocking {
            assertEquals(dataSource.getMinFavoriteCosmeticsPrice(), PRICE_MIN_VAL)
        }

    @Test
    fun `get max favorite cosmetics price EXPECT max favorite cosmetics price from database`() =
        runBlocking {
            assertEquals(dataSource.getMaxFavoriteCosmeticsPrice(), PRICE_MAX_VAL)
        }
}