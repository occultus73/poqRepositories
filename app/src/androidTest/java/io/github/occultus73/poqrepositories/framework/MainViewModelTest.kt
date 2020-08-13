package io.github.occultus73.poqrepositories.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.occultus73.poqrepositories.business.domain.model.SquareReposItem
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import io.github.occultus73.poqrepositories.business.interactors.GetSquareReposItems
import io.github.occultus73.poqrepositories.framework.presentation.state.MainStateEvent
import io.github.occultus73.poqrepositories.framework.presentation.state.MainViewState
import io.github.occultus73.poqrepositories.framework.presentation.viewmodels.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@ExperimentalCoroutinesApi
@RunWith(BlockJUnit4ClassRunner::class)
class MainViewModelTest {

    private lateinit var classUnderTest: MainViewModel

    @MockK
    lateinit var getSquareReposItems: GetSquareReposItems

    @MockK
    lateinit var getSquareReposEvent: MainStateEvent.GetSquareReposEvent

    @MockK(relaxed = true)
    lateinit var flowDataStateMainViewState: Flow<DataState<MainViewState>>

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { getSquareReposItems.execute() } returns flowDataStateMainViewState
        classUnderTest = MainViewModel(getSquareReposItems)
    }

    @Test
    fun test_viewModel_setStateEvent(){
        classUnderTest.setStateEvent(getSquareReposEvent)
        coVerify { getSquareReposItems.execute() }
    }

    @Test
    fun test_viewModel_getCurrentViewState(){
        val viewState = classUnderTest.getCurrentViewState()
        assert(viewState == MainViewState())
    }

    @Test
    fun test_viewModel_setViewState_getCurrentViewState(){
        val viewState = MainViewState(listOf(SquareReposItem(9, "desc", "nam")))
        classUnderTest.setViewState(viewState)
        assert(classUnderTest.getCurrentViewState() == viewState)
    }


}