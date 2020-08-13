package io.github.occultus73.poqrepositories.business

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.occultus73.poqrepositories.business.data.cache.CacheDataSource
import io.github.occultus73.poqrepositories.business.data.network.NetworkDataSource
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import io.github.occultus73.poqrepositories.business.interactors.GetSquareReposItems
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class GetSquareReposItemsTest {

    private lateinit var classUnderTest: GetSquareReposItems

    @MockK
    lateinit var cacheDataSource: CacheDataSource

    @MockK
    lateinit var networkDataSource: NetworkDataSource

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val sampleSquareReposItem = SquareReposItem(4, "desc", "name")

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { networkDataSource.get() } returns listOf(sampleSquareReposItem)
        coEvery { cacheDataSource.insertList(any()) } just runs
        coEvery { cacheDataSource.get() } returns listOf(sampleSquareReposItem)
        classUnderTest = GetSquareReposItems(cacheDataSource, networkDataSource)
    }

    @Test
    fun test_execute() = runBlocking {
        classUnderTest.execute().onEach {
            assert(it is DataState.Success)
        }
        return@runBlocking
    }


}