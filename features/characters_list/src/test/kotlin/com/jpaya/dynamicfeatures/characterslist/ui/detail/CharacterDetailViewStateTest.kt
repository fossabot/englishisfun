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
