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
class CosmeticsRemoteDataSourceImplTest {
    private val api: CosmeticsApi = mockk {
        coEvery { getCosmetics() } returns ALL_COSMETICS
    }

    private val dataSource = CosmeticsRemoteDataSourceImpl(api)

    @Test
    fun `get all cosmetics EXPECT all cosmetics from api`() = runBlocking {
        assertEquals(dataSource.getCosmetics(), ALL_COSMETICS)
    }
}
