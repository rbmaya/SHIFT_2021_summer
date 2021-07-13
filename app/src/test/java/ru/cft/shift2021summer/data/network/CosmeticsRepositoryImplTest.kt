package ru.cft.shift2021summer.data.network

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.cft.shift2021summer.data.TestEnitities.ALL_COSMETICS

@RunWith(MockitoJUnitRunner::class)
class CosmeticsRepositoryImplTest {
    private val cosmeticsDataSource: CosmeticsDataSource = mockk {
        coEvery { getCosmetics() } returns ALL_COSMETICS
    }

    private val repository = CosmeticsRepositoryImpl(cosmeticsDataSource)

    @Test
    fun `get all cosmetics EXPECT call get all cosmetics datasource`() = runBlocking {
        assertEquals(repository.getCosmetics(), ALL_COSMETICS)
    }
}