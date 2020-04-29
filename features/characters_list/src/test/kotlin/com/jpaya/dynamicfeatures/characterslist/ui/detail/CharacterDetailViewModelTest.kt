package com.jpaya.dynamicfeatures.characterslist.ui.detail

import androidx.lifecycle.Observer
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.core.network.responses.BaseResponse
import com.jpaya.core.network.responses.CharacterResponse
import com.jpaya.dynamicfeatures.characterslist.ui.detail.model.CharacterDetail
import com.jpaya.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class CharacterDetailViewModelTest {

    @MockK(relaxed = true)
    lateinit var marvelRepository: MarvelRepository
    @MockK(relaxed = true)
    lateinit var characterFavoriteRepository: CharacterFavoriteRepository
    @MockK
    lateinit var characterDetailMapper: CharacterDetailMapper
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharacterDetailViewState>
    @MockK(relaxed = true)
    lateinit var dataObserver: Observer<CharacterDetail>
    lateinit var viewModel: CharacterDetailViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = characterFavoriteRepository,
            characterDetailMapper = characterDetailMapper
        )
        viewModel.state.observeForever(stateObserver)
        viewModel.data.observeForever(dataObserver)
    }

    @Test
    fun loadCharacterDetail_ShouldSetLoadingState() {
        viewModel.loadCharacterDetail(1L)

        verify { stateObserver.onChanged(CharacterDetailViewState.Loading) }
    }

    @Test
    fun loadCharacterDetail_WhenError_ShouldBeErrorState() {
        viewModel.loadCharacterDetail(1L)

        val expectedState: CharacterDetailViewState = CharacterDetailViewState.Error
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun loadCharacterDetail_WhenSuccess_ShouldPostDataResult() {
        val characterDetail = mockk<CharacterDetail>()
        val characterResponse = mockk<BaseResponse<CharacterResponse>>()
        coEvery { marvelRepository.getCharacter(any()) } returns characterResponse
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        val characterId = 1L
        viewModel.loadCharacterDetail(characterId)

        verify { dataObserver.onChanged(characterDetail) }
        coVerify { marvelRepository.getCharacter(characterId) }
        coVerify { characterDetailMapper.map(characterResponse) }
    }

    @Test
    fun loadCharacterDetail_NonFavourite_WhenSuccess_ShouldBeAddToFavoriteState() {
        val characterDetail = mockk<CharacterDetail>()
        coEvery { characterFavoriteRepository.getCharacterFavorite(any()) } returns null
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)

        val expectedState = CharacterDetailViewState.AddToFavorite
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun loadCharacterDetail_Favourite_WhenSuccess_ShouldBeAlreadyAddedToFavoriteState() {
        val characterDetail = mockk<CharacterDetail>()
        coEvery { characterFavoriteRepository.getCharacterFavorite(any()) } returns mockk()
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)

        val expectedState = CharacterDetailViewState.AlreadyAddedToFavorite
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun addCharacterToFavorite_WhenNotLoadedDetail_ShouldDoNothing() {
        viewModel.addCharacterToFavorite()

        coVerify(exactly = 0) {
            characterFavoriteRepository.insertCharacterFavorite(any(), any(), any())
        }
        verify(exactly = 0) { stateObserver.onChanged(any()) }
    }

    @Test
    fun addCharacterToFavorite_WhenLoadedDetail_ShouldBeAddedToFavorite() {
        val characterDetail = CharacterDetail(
            id = 1011334,
            name = "3-D Man",
            description = "",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
        coEvery { marvelRepository.getCharacter(any()) } returns mockk()
        coEvery { characterDetailMapper.map(any()) } returns characterDetail

        viewModel.loadCharacterDetail(1L)
        viewModel.addCharacterToFavorite()

        val expectedState = CharacterDetailViewState.AddedToFavorite
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
        coVerify {
            characterFavoriteRepository.insertCharacterFavorite(
                id = characterDetail.id,
                name = characterDetail.name,
                imageUrl = characterDetail.imageUrl
            )
        }
    }

    @Test
    fun dismissCharacterDetail_ShouldBeDismissState() {
        viewModel.dismissCharacterDetail()

        val expectedState = CharacterDetailViewState.Dismiss
        assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }
    }
}
