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

package com.jpaya.dynamicfeatures.characterslist.ui.detail

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CharacterDetailViewStateTest {

    private lateinit var state: CharacterDetailViewState

    @Test
    fun setStateAsLoading_ShouldBeSettled() {
        state = CharacterDetailViewState.Loading

        assertTrue(state.isLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddToFavorite())
        assertFalse(state.isAddedToFavorite())
        assertFalse(state.isAlreadyAddedToFavorite())
        assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsError_ShouldBeSettled() {
        state = CharacterDetailViewState.Error

        assertTrue(state.isError())
        assertFalse(state.isLoading())
        assertFalse(state.isAddToFavorite())
        assertFalse(state.isAddedToFavorite())
        assertFalse(state.isAlreadyAddedToFavorite())
        assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAddToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AddToFavorite

        assertTrue(state.isAddToFavorite())
        assertFalse(state.isLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddedToFavorite())
        assertFalse(state.isAlreadyAddedToFavorite())
        assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAddedToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AddedToFavorite

        assertTrue(state.isAddedToFavorite())
        assertFalse(state.isLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddToFavorite())
        assertFalse(state.isAlreadyAddedToFavorite())
        assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAlreadyAddedToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AlreadyAddedToFavorite

        assertTrue(state.isAlreadyAddedToFavorite())
        assertFalse(state.isLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddToFavorite())
        assertFalse(state.isAddedToFavorite())
        assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsDismiss_ShouldBeSettled() {
        state = CharacterDetailViewState.Dismiss

        assertTrue(state.isDismiss())
        assertFalse(state.isLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddToFavorite())
        assertFalse(state.isAddedToFavorite())
        assertFalse(state.isAlreadyAddedToFavorite())
    }
}
