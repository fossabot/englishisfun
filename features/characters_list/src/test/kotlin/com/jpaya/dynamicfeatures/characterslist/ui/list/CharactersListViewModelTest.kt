package com.jpaya.dynamicfeatures.characterslist.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpaya.core.network.NetworkState
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSource
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class CharactersListViewModelTest {

    @MockK(relaxed = true)
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharactersListViewState>
    @MockK(relaxed = true)
    lateinit var eventObserver: Observer<CharactersListViewEvent>
    private lateinit var viewModel: CharactersListViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun networkSuccessEmptyCharacters_ShouldBeEmptyState() {
        val networkState = NetworkState.Success(
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Empty
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalEmptyCharacters_ShouldBeNoMoreElementsState() {
        val networkState = NetworkState.Success(
            isAdditional = true,
            isEmptyResponse = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.NoMoreElements
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessAdditionalCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkSuccessCharacters_ShouldBeLoadedState() {
        val networkState = NetworkState.Success()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loaded
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkLoadingCharacters_ShouldBeLoadingState() {
        val networkState = NetworkState.Loading()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Loading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalLoadingCharacters_ShouldBeAddLoadingState() {
        val networkState = NetworkState.Loading(
            isAdditional = true
        )
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddLoading
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkErrorCharacters_ShouldBeErrorState() {
        val networkState = NetworkState.Error()
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.Error
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun networkAdditionalErrorCharacters_ShouldBeAddErrorState() {
        val networkState = NetworkState.Error(true)
        val fakePageDataSource = FakeCharactersPageDataSource(networkState)
        val fakeSourceLiveData = MutableLiveData<CharactersPageDataSource>(fakePageDataSource)
        every {
            dataSourceFactory.sourceLiveData
        } returns fakeSourceLiveData

        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.state.observeForever(stateObserver)

        val expectedState = CharactersListViewState.AddError
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun refreshCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.refreshLoadedCharactersList()

        verify { dataSourceFactory.refresh() }
        verify(exactly = 0) { dataSourceFactory.retry() }
    }

    @Test
    fun retryCharacterList_ShouldInvokeDataSourceMethod() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.retryAddCharactersList()

        verify { dataSourceFactory.retry() }
        verify(exactly = 0) { dataSourceFactory.refresh() }
    }

    @Test
    fun openCharacterDetail_ShouldSendAsEvent() {
        viewModel = CharactersListViewModel(dataSourceFactory = dataSourceFactory)
        viewModel.event.observeForever(eventObserver)

        val characterId = 1L
        viewModel.openCharacterDetail(characterId)

        val expectedEvent = CharactersListViewEvent.OpenCharacterDetail(characterId)
        assertEquals(expectedEvent, viewModel.event.value)
        verify { eventObserver.onChanged(expectedEvent) }
    }

    inner class FakeCharactersPageDataSource(
        forceNetworkState: NetworkState
    ) : CharactersPageDataSource(
        repository = mockk(),
        scope = mockk(),
        mapper = mockk()
    ) {
        init {
            networkState.postValue(forceNetworkState)
        }
    }
}
