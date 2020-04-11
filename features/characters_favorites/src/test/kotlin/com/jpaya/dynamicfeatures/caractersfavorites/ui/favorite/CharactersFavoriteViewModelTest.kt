package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewState
import com.jpaya.libraries.testutils.rules.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersFavoriteViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var repository: CharacterFavoriteRepository
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharactersFavoriteViewState>
    lateinit var viewModel: CharactersFavoriteViewModel

    private val data = MutableLiveData<List<CharacterFavorite>>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every {
            repository.getAllCharactersFavoriteLiveData()
        } returns data

        viewModel = CharactersFavoriteViewModel(characterFavoriteRepository = repository)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun initializeViewModel_ShouldObtainAllFavorite() {
        coVerify {
            repository.getAllCharactersFavoriteLiveData()
        }
    }

    @Test
    fun removeFavoriteCharacter_ShouldInvokeRepositoryDeleteMethod() {
        val character = mockk<CharacterFavorite>()
        viewModel.removeFavoriteCharacter(character)

        coVerify {
            repository.deleteCharacterFavorite(character)
        }
    }

    @Test
    fun emptyCharactersFavorite_ShouldBeEmptyState() {
        data.postValue(listOf())

        val expectedState = CharactersFavoriteViewState.Empty
        assertEquals(expectedState, viewModel.state.value)
        coVerify {
            stateObserver.onChanged(expectedState)
        }
    }

    @Test
    fun addedCharactersFavorite_ShouldBeListedState() {
        val favoriteCharacter = mockk<CharacterFavorite>()
        data.postValue(listOf(favoriteCharacter))

        val expectedState = CharactersFavoriteViewState.Listed
        assertEquals(expectedState, viewModel.state.value)
        coVerify {
            stateObserver.onChanged(expectedState)
        }
    }
}
