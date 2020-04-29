/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewState
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class CharactersFavoriteViewModelTest {

    @MockK(relaxed = true)
    lateinit var repository: CharacterFavoriteRepository
    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<CharactersFavoriteViewState>
    private lateinit var viewModel: CharactersFavoriteViewModel

    private val data = MutableLiveData<List<CharacterFavorite>>()

    @BeforeEach
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
