package ru.cft.shift2021summer.data.caching

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_BRAND1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_NAME1
import ru.cft.shift2021summer.data.TestEnitities.COSMETICS_BY_PRODUCT_TYPE1
import ru.cft.shift2021summer.data.TestEnitities.COSMETIC_1
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS
import ru.cft.shift2021summer.data.TestEnitities.FAVORITE_COSMETICS_BY_PRICE
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MAX_VAL
import ru.cft.shift2021summer.data.TestEnitities.PRICE_MIN_VAL

@RunWith(MockitoJUnitRunner::class)
class SavedCosmeticsRepositoryImplTest {
    private val dataSource: SavedCosmeticsDataSource = mockk {
        coEvery { saveCosmetics(ALL_COSMETICS) } just runs
        coEvery { getCosmetics() } returns ALL_COSMETICS
        coEvery { getCosmeticByName(COSMETIC_1.name) } returns COSMETICS_BY_NAME1
        coEvery { getCosmeticByBrand(COSMETIC_1.brand) } returns COSMETICS_BY_BRAND1
        coEvery { getCosmeticByProductType(COSMETIC_1.productType) } returns COSMETICS_BY_PRODUCT_TYPE1
        coEvery {
            getCosmeticsByPrice(
                PRICE_MIN_VAL,
                PRICE_MAX_VAL
            )
        } returns TestEnitities.COSMETICS_BY_PRICE
        coEvery { getMinCosmeticsPrice() } returns PRICE_MIN_VAL
        coEvery { getMaxCosmeticsPrice() } returns PRICE_MAX_VAL
        coEvery { changeCosmeticIsFavorite(TestEnitities.COSMETIC_1_IS_FAVORITE) } just runs
        coEvery { getFavoriteCosmetics() } returns TestEnitities.FAVORITE_COSMETICS
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

    private val repository = SavedCosmeticsRepositoryImpl(dataSource)

    @Test
    fun `save cosmetics EXPECT call save cosmetics datasource`() = runBlocking {
        repository.saveCosmetics(ALL_COSMETICS)
        coVerify {
            dataSource.saveCosmetics(ALL_COSMETICS)
        }
    }

    @Test
    fun `get cosmetics EXPECT call get cosmetics datasource`() = runBlocking {
        Assert.assertEquals(repository.getCosmetics(), ALL_COSMETICS)
    }

    @Test
    fun `get cosmetics by name EXPECT call get cosmetics by name datasource`() = runBlocking {
        Assert.assertEquals(repository.getCosmeticByName(COSMETIC_1.name), COSMETICS_BY_NAME1)
    }

    @Test
    fun `get cosmetics by brand EXPECT call get cosmetics by brand datasource`() = runBlocking {
        Assert.assertEquals(repository.getCosmeticByBrand(COSMETIC_1.brand), COSMETICS_BY_BRAND1)
    }

    @Test
    fun `get cosmetics by product type EXPECT call get cosmetics by product type datasource`() =
        runBlocking {
            Assert.assertEquals(
                repository.getCosmeticByProductType(COSMETIC_1.productType),
                COSMETICS_BY_PRODUCT_TYPE1
            )
        }

    @Test
    fun `get cosmetics by price EXPECT call get cosmetics by price datasource`() = runBlocking {
        Assert.assertEquals(
            repository.getCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL),
            TestEnitities.COSMETICS_BY_PRICE
        )
    }

    @Test
    fun `get min cosmetics price EXPECT call get min cosmetics price datasource`() = runBlocking {
        Assert.assertEquals(repository.getMinCosmeticsPrice(), PRICE_MIN_VAL)
    }

    @Test
    fun `get max cosmetics price EXPECT call get max cosmetics price datasource`() = runBlocking {
        Assert.assertEquals(repository.getMaxCosmeticsPrice(), PRICE_MAX_VAL)
    }

    @Test
    fun `change cosmetic is favorite EXPECT call change cosmetic datasource`() = runBlocking {
        repository.changeCosmeticIsFavorite(TestEnitities.COSMETIC_1_IS_FAVORITE)
        coVerify {
            dataSource.changeCosmeticIsFavorite(TestEnitities.COSMETIC_1_IS_FAVORITE)
        }
    }

    @Test
    fun `get favorite cosmetics EXPECT call get favorite cosmetics datasource`() = runBlocking {
        Assert.assertEquals(repository.getFavoriteCosmetics(), FAVORITE_COSMETICS)
    }

    @Test
    fun `get favorite cosmetics by name EXPECT call get favorite cosmetics by name datasource`() =
        runBlocking {
            Assert.assertEquals(
                repository.getFavoriteCosmeticsByName(COSMETIC_1.name),
                COSMETICS_BY_NAME1
            )
        }

    @Test
    fun `get favorite cosmetics by brand EXPECT call get favorite cosmetics by brand datasource`() =
        runBlocking {
            Assert.assertEquals(
                repository.getFavoriteCosmeticsByBrand(COSMETIC_1.brand),
                COSMETICS_BY_BRAND1
            )
        }

    @Test
    fun `get favorite cosmetics by product type EXPECT call get favorite cosmetics by product type datasource`() =
        runBlocking {
            Assert.assertEquals(
                repository.getFavoriteCosmeticsByProductType(COSMETIC_1.productType),
                COSMETICS_BY_PRODUCT_TYPE1
            )
        }

    @Test
    fun `get favorite cosmetics by price EXPECT call get favorite cosmetics by price datasoource`() =
        runBlocking {
            Assert.assertEquals(
                repository.getFavoriteCosmeticsByPrice(PRICE_MIN_VAL, PRICE_MAX_VAL),
                FAVORITE_COSMETICS_BY_PRICE
            )
        }

    @Test
    fun `get min favorite cosmetics price EXPECT call get min favorite cosmetics price datasource`() =
        runBlocking {
            Assert.assertEquals(repository.getMinFavoriteCosmeticsPrice(), PRICE_MIN_VAL)
        }

    @Test
    fun `get max favorite cosmetics price EXPECT call get max favorite cosmetics price datasource`() =
        runBlocking {
            Assert.assertEquals(repository.getMaxFavoriteCosmeticsPrice(), PRICE_MAX_VAL)
        }

}